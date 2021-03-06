/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.warehouse.web.internal.display.context;

import com.liferay.commerce.configuration.CommerceShippingGroupServiceConfiguration;
import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.definitions.web.servlet.taglib.ui.CPDefinitionScreenNavigationConstants;
import com.liferay.commerce.product.display.context.util.CPRequestHelper;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.service.CommerceWarehouseItemService;
import com.liferay.commerce.service.CommerceWarehouseService;
import com.liferay.commerce.util.comparator.CommerceWarehouseNameComparator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Andrea Di Giorgi
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceWarehouseItemsDisplayContext {

	public CommerceWarehouseItemsDisplayContext(
		CommerceWarehouseItemService commerceWarehouseItemService,
		CommerceWarehouseService commerceWarehouseService,
		ConfigurationProvider configurationProvider,
		CPInstanceService cpInstanceService,
		HttpServletRequest httpServletRequest, Portal portal,
		PortletResourcePermission portletResourcePermission) {

		_commerceWarehouseItemService = commerceWarehouseItemService;
		_commerceWarehouseService = commerceWarehouseService;
		_configurationProvider = configurationProvider;
		_cpInstanceService = cpInstanceService;
		_portal = portal;
		_portletResourcePermission = portletResourcePermission;

		_cpRequestHelper = new CPRequestHelper(httpServletRequest);
	}

	public String getBackURL() throws PortalException {
		RenderRequest renderRequest = _cpRequestHelper.getRenderRequest();

		String lifecycle = (String)renderRequest.getAttribute(
			LiferayPortletRequest.LIFECYCLE_PHASE);

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			renderRequest, CPPortletKeys.CP_DEFINITIONS, lifecycle);

		portletURL.setParameter(
			"mvcRenderCommandName", "editProductDefinition");

		CPInstance cpInstance = getCPInstance();

		portletURL.setParameter(
			"cpDefinitionId", String.valueOf(cpInstance.getCPDefinitionId()));

		portletURL.setParameter(
			"screenNavigationCategoryKey",
			CPDefinitionScreenNavigationConstants.CATEGORY_KEY_SKUS);

		return portletURL.toString();
	}

	public CommerceWarehouseItem getCommerceWarehouseItem(
			CommerceWarehouse commerceWarehouse)
		throws PortalException {

		CPInstance cpInstance = getCPInstance();

		return _commerceWarehouseItemService.fetchCommerceWarehouseItem(
			commerceWarehouse.getCommerceWarehouseId(),
			cpInstance.getCPInstanceId());
	}

	public List<CommerceWarehouse> getCommerceWarehouses()
		throws PortalException {

		return _getCommerceWarehouses();
	}

	public CPInstance getCPInstance() throws PortalException {
		if (_cpInstance == null) {
			long cpInstanceId = ParamUtil.getLong(
				_cpRequestHelper.getRenderRequest(), "cpInstanceId");

			_cpInstance = _cpInstanceService.getCPInstance(cpInstanceId);
		}

		return _cpInstance;
	}

	public String getUpdateCommerceWarehouseItemTaglibOnClick(
		long commerceWarehouseId, long commerceWarehouseItemId, int index) {

		RenderResponse renderResponse = _cpRequestHelper.getRenderResponse();

		StringBundler sb = new StringBundler(10);

		sb.append(renderResponse.getNamespace());
		sb.append("updateCommerceWarehouseItem");
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(commerceWarehouseId);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append(commerceWarehouseItemId);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append(index);
		sb.append(StringPool.CLOSE_PARENTHESIS);
		sb.append(StringPool.SEMICOLON);

		return sb.toString();
	}

	public boolean hasManageCommerceWarehousePermission() {
		return _portletResourcePermission.contains(
			_cpRequestHelper.getPermissionChecker(),
			_cpRequestHelper.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);
	}

	private List<CommerceWarehouse> _getCommerceWarehouses()
		throws PortalException {

		if (_commerceWarehouses != null) {
			return _commerceWarehouses;
		}

		CommerceShippingGroupServiceConfiguration
			commerceShippingGroupServiceConfiguration =
				_configurationProvider.getConfiguration(
					CommerceShippingGroupServiceConfiguration.class,
					new GroupServiceSettingsLocator(
						_cpRequestHelper.getScopeGroupId(),
						CommerceConstants.SHIPPING_SERVICE_NAME));

		String commerceShippingOriginLocatorKey =
			commerceShippingGroupServiceConfiguration.
				commerceShippingOriginLocatorKey();

		if (commerceShippingOriginLocatorKey.equals("address")) {
			CommerceWarehouse commerceWarehouse =
				_commerceWarehouseService.fetchDefaultCommerceWarehouse(
					_cpRequestHelper.getScopeGroupId());

			if (commerceWarehouse == null) {
				_commerceWarehouses = Collections.emptyList();
			}
			else {
				_commerceWarehouses = Collections.singletonList(
					commerceWarehouse);
			}

			return _commerceWarehouses;
		}

		_commerceWarehouses = _commerceWarehouseService.getCommerceWarehouses(
			_cpRequestHelper.getScopeGroupId(), true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new CommerceWarehouseNameComparator(true));

		return _commerceWarehouses;
	}

	private final CommerceWarehouseItemService _commerceWarehouseItemService;
	private List<CommerceWarehouse> _commerceWarehouses;
	private final CommerceWarehouseService _commerceWarehouseService;
	private final ConfigurationProvider _configurationProvider;
	private CPInstance _cpInstance;
	private final CPInstanceService _cpInstanceService;
	private final CPRequestHelper _cpRequestHelper;
	private final Portal _portal;
	private final PortletResourcePermission _portletResourcePermission;

}