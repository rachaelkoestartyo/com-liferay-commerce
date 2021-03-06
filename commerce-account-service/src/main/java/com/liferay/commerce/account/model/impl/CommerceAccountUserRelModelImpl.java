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

package com.liferay.commerce.account.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.model.CommerceAccountUserRelModel;
import com.liferay.commerce.account.model.CommerceAccountUserRelSoap;
import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the CommerceAccountUserRel service. Represents a row in the &quot;CommerceAccountUserRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CommerceAccountUserRelModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceAccountUserRelImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceAccountUserRelImpl
 * @see CommerceAccountUserRel
 * @see CommerceAccountUserRelModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class CommerceAccountUserRelModelImpl extends BaseModelImpl<CommerceAccountUserRel>
	implements CommerceAccountUserRelModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce account user rel model instance should use the {@link CommerceAccountUserRel} interface instead.
	 */
	public static final String TABLE_NAME = "CommerceAccountUserRel";
	public static final Object[][] TABLE_COLUMNS = {
			{ "commerceAccountId", Types.BIGINT },
			{ "commerceAccountUserId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("commerceAccountId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("commerceAccountUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table CommerceAccountUserRel (commerceAccountId LONG not null,commerceAccountUserId LONG not null,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,primary key (commerceAccountId, commerceAccountUserId))";
	public static final String TABLE_SQL_DROP = "drop table CommerceAccountUserRel";
	public static final String ORDER_BY_JPQL = " ORDER BY commerceAccountUserRel.userId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY CommerceAccountUserRel.userId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.account.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.commerce.account.model.CommerceAccountUserRel"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.account.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.commerce.account.model.CommerceAccountUserRel"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.commerce.account.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.commerce.account.model.CommerceAccountUserRel"),
			true);
	public static final long COMMERCEACCOUNTID_COLUMN_BITMASK = 1L;
	public static final long COMMERCEACCOUNTUSERID_COLUMN_BITMASK = 2L;
	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CommerceAccountUserRel toModel(
		CommerceAccountUserRelSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CommerceAccountUserRel model = new CommerceAccountUserRelImpl();

		model.setCommerceAccountId(soapModel.getCommerceAccountId());
		model.setCommerceAccountUserId(soapModel.getCommerceAccountUserId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CommerceAccountUserRel> toModels(
		CommerceAccountUserRelSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CommerceAccountUserRel> models = new ArrayList<CommerceAccountUserRel>(soapModels.length);

		for (CommerceAccountUserRelSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.commerce.account.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.commerce.account.model.CommerceAccountUserRel"));

	public CommerceAccountUserRelModelImpl() {
	}

	@Override
	public CommerceAccountUserRelPK getPrimaryKey() {
		return new CommerceAccountUserRelPK(_commerceAccountId,
			_commerceAccountUserId);
	}

	@Override
	public void setPrimaryKey(CommerceAccountUserRelPK primaryKey) {
		setCommerceAccountId(primaryKey.commerceAccountId);
		setCommerceAccountUserId(primaryKey.commerceAccountUserId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new CommerceAccountUserRelPK(_commerceAccountId,
			_commerceAccountUserId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((CommerceAccountUserRelPK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return CommerceAccountUserRel.class;
	}

	@Override
	public String getModelClassName() {
		return CommerceAccountUserRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("commerceAccountId", getCommerceAccountId());
		attributes.put("commerceAccountUserId", getCommerceAccountUserId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long commerceAccountId = (Long)attributes.get("commerceAccountId");

		if (commerceAccountId != null) {
			setCommerceAccountId(commerceAccountId);
		}

		Long commerceAccountUserId = (Long)attributes.get(
				"commerceAccountUserId");

		if (commerceAccountUserId != null) {
			setCommerceAccountUserId(commerceAccountUserId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@JSON
	@Override
	public long getCommerceAccountId() {
		return _commerceAccountId;
	}

	@Override
	public void setCommerceAccountId(long commerceAccountId) {
		_columnBitmask |= COMMERCEACCOUNTID_COLUMN_BITMASK;

		if (!_setOriginalCommerceAccountId) {
			_setOriginalCommerceAccountId = true;

			_originalCommerceAccountId = _commerceAccountId;
		}

		_commerceAccountId = commerceAccountId;
	}

	public long getOriginalCommerceAccountId() {
		return _originalCommerceAccountId;
	}

	@JSON
	@Override
	public long getCommerceAccountUserId() {
		return _commerceAccountUserId;
	}

	@Override
	public void setCommerceAccountUserId(long commerceAccountUserId) {
		_columnBitmask |= COMMERCEACCOUNTUSERID_COLUMN_BITMASK;

		if (!_setOriginalCommerceAccountUserId) {
			_setOriginalCommerceAccountUserId = true;

			_originalCommerceAccountUserId = _commerceAccountUserId;
		}

		_commerceAccountUserId = commerceAccountUserId;
	}

	@Override
	public String getCommerceAccountUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getCommerceAccountUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setCommerceAccountUserUuid(String commerceAccountUserUuid) {
	}

	public long getOriginalCommerceAccountUserId() {
		return _originalCommerceAccountUserId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask = -1L;

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public CommerceAccountUserRel toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CommerceAccountUserRel)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CommerceAccountUserRelImpl commerceAccountUserRelImpl = new CommerceAccountUserRelImpl();

		commerceAccountUserRelImpl.setCommerceAccountId(getCommerceAccountId());
		commerceAccountUserRelImpl.setCommerceAccountUserId(getCommerceAccountUserId());
		commerceAccountUserRelImpl.setCompanyId(getCompanyId());
		commerceAccountUserRelImpl.setUserId(getUserId());
		commerceAccountUserRelImpl.setUserName(getUserName());
		commerceAccountUserRelImpl.setCreateDate(getCreateDate());
		commerceAccountUserRelImpl.setModifiedDate(getModifiedDate());

		commerceAccountUserRelImpl.resetOriginalValues();

		return commerceAccountUserRelImpl;
	}

	@Override
	public int compareTo(CommerceAccountUserRel commerceAccountUserRel) {
		int value = 0;

		if (getUserId() < commerceAccountUserRel.getUserId()) {
			value = -1;
		}
		else if (getUserId() > commerceAccountUserRel.getUserId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommerceAccountUserRel)) {
			return false;
		}

		CommerceAccountUserRel commerceAccountUserRel = (CommerceAccountUserRel)obj;

		CommerceAccountUserRelPK primaryKey = commerceAccountUserRel.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CommerceAccountUserRelModelImpl commerceAccountUserRelModelImpl = this;

		commerceAccountUserRelModelImpl._originalCommerceAccountId = commerceAccountUserRelModelImpl._commerceAccountId;

		commerceAccountUserRelModelImpl._setOriginalCommerceAccountId = false;

		commerceAccountUserRelModelImpl._originalCommerceAccountUserId = commerceAccountUserRelModelImpl._commerceAccountUserId;

		commerceAccountUserRelModelImpl._setOriginalCommerceAccountUserId = false;

		commerceAccountUserRelModelImpl._setModifiedDate = false;

		commerceAccountUserRelModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CommerceAccountUserRel> toCacheModel() {
		CommerceAccountUserRelCacheModel commerceAccountUserRelCacheModel = new CommerceAccountUserRelCacheModel();

		commerceAccountUserRelCacheModel.commerceAccountUserRelPK = getPrimaryKey();

		commerceAccountUserRelCacheModel.commerceAccountId = getCommerceAccountId();

		commerceAccountUserRelCacheModel.commerceAccountUserId = getCommerceAccountUserId();

		commerceAccountUserRelCacheModel.companyId = getCompanyId();

		commerceAccountUserRelCacheModel.userId = getUserId();

		commerceAccountUserRelCacheModel.userName = getUserName();

		String userName = commerceAccountUserRelCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			commerceAccountUserRelCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			commerceAccountUserRelCacheModel.createDate = createDate.getTime();
		}
		else {
			commerceAccountUserRelCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			commerceAccountUserRelCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			commerceAccountUserRelCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return commerceAccountUserRelCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{commerceAccountId=");
		sb.append(getCommerceAccountId());
		sb.append(", commerceAccountUserId=");
		sb.append(getCommerceAccountUserId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.commerce.account.model.CommerceAccountUserRel");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>commerceAccountId</column-name><column-value><![CDATA[");
		sb.append(getCommerceAccountId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>commerceAccountUserId</column-name><column-value><![CDATA[");
		sb.append(getCommerceAccountUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CommerceAccountUserRel.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CommerceAccountUserRel.class, ModelWrapper.class
		};
	private long _commerceAccountId;
	private long _originalCommerceAccountId;
	private boolean _setOriginalCommerceAccountId;
	private long _commerceAccountUserId;
	private long _originalCommerceAccountUserId;
	private boolean _setOriginalCommerceAccountUserId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _columnBitmask;
	private CommerceAccountUserRel _escapedModel;
}