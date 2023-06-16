package com.a.n.base.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.vs.rappit.base.dal.providers.PersistenceType;
import com.vs.rappit.base.acl.AllowedFields;
import org.apache.commons.lang3.BooleanUtils;
import com.vs.rappit.base.authentication.logic.AppUserPrivilegeCache;
import org.springframework.beans.factory.annotation.Autowired;
import com.vs.rappit.base.acl.IPerimeterManager;
import com.a.n.base.model.ApplicationUserBase;

public abstract class ApplicationUserPerimeterBaseImpl<T extends ApplicationUserBase> implements IPerimeterManager<T> {
	
	@Autowired
	private AppUserPrivilegeCache userCache;
	
	@Override
	public boolean canCreate(T model) {
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		if (BooleanUtils.isTrue(userBase.isDevAdmin())) { return true; }
		
		return false;
	}

	@Override
	public boolean canUpdate(T model) {
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		if (BooleanUtils.isTrue(userBase.isDevAdmin())) { return true; }
		
		return false;
	}

	@Override
	public boolean canDelete(T model) {
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		if (BooleanUtils.isTrue(userBase.isDevAdmin())) { return true; }
		
		return false;
	}

	@Override
	public boolean canRead(T model) {
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		if (BooleanUtils.isTrue(userBase.isDevAdmin())) { return true; }
		if(userBase.getEmail().equalsIgnoreCase(model.getEmail())) { return true; }
		
		return false;
	}

	@Override
	public String getAccessQuery(PersistenceType type) {
		return null;
	}

	@Override
	public AllowedFields getSelectFields(PersistenceType type) {
		AllowedFields allowedFields = new AllowedFields();
		ApplicationUserBase userBase = (ApplicationUserBase) userCache.getCurrentUser();
		setReadFields(userBase, allowedFields);
		setWriteFields(userBase, allowedFields);
		return allowedFields;
	}
	
	protected void setReadFields(ApplicationUserBase userBase, AllowedFields allowedFields) {
		List<String> allowedAccessFields = new ArrayList<>();
		allowedAccessFields.addAll(getTechnicalFields());
		if(BooleanUtils.isTrue(userBase.isDevAdmin()) ||  BooleanUtils.isTrue(userBase.isLogin())) {
			allowedAccessFields.add("*");
			allowedFields.setAllowedReadFields(allowedAccessFields);
			return;
		}
		
		allowedFields.setAllowedReadFields(allowedAccessFields);
	}
	protected void setWriteFields(ApplicationUserBase userBase, AllowedFields allowedFields) {
		List<String> allowedAccessFields = new ArrayList<>();
		allowedAccessFields.addAll(getTechnicalFields());
		if(BooleanUtils.isTrue(userBase.isDevAdmin())) {
			allowedAccessFields.add("*");
			allowedFields.setAllowedWriteFields(allowedAccessFields);
			return;
		}
		
		allowedFields.setAllowedWriteFields(allowedAccessFields);
	}
	protected List<String> getTechnicalFields() {
		String[] technicalFields = {"sid", "createdBy", "createdDate", "modifiedBy", "modifiedDate", "recDeleted", "isLogin", "emailInLowerCase", "userPreference", "devAdmin", "userRoles"};
		return Arrays.asList(technicalFields);
	}
	
	
}
