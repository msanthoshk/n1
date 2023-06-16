package com.a.n.service;

import com.a.n.exception.ErrorMessages;

import com.a.n.exception.ErrorCodes;
import com.vs.rappit.base.logger.Logger;
import com.vs.rappit.base.logger.LoggerFactory;
import com.vs.rappit.base.authentication.Authenticator;
import com.vs.rappit.base.cache.CacheManager;
import com.vs.rappit.base.acl.IPerimeterManager;
import com.vs.rappit.base.exception.ForbiddenException;
import com.vs.rappit.base.appconfiguration.AppConfigurationCache;
import com.vs.rappit.base.authentication.logic.AppUserPrivilegeCache;
// import com.vs.rappit.base.user.menu.ApplicationMenuLoader;
import com.vs.rappit.base.cache.MenuCache;
import com.vs.rappit.base.user.menu.MenuRoleUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.a.n.model.ApplicationUser;
import com.a.n.base.service.ApplicationUserBaseService;
import com.a.n.service.IApplicationUserService;

import com.vs.rappit.base.authentication.UserContextThreadLocal;

@Service("applicationUserService")
public class ApplicationUserService extends ApplicationUserBaseService<ApplicationUser> 
implements IApplicationUserService<ApplicationUser>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationUserService.class);

	@Autowired
	private MenuRoleUtil menuRoleUtil;

	@Autowired
	private MenuCache menuCache;

	@Autowired
	private ApplicationUserPerimeterImpl applicationUserPerimeterImpl;

	public ApplicationUserService(ChangelogService changelogService) {
		super(ApplicationUser.class);
		setChangelogService(changelogService);
	}

	public ApplicationUser getCurrentUserWithMenu() {
		ApplicationUser user = getCurrentUser();
		if (null == user) {
			LOGGER.error(ErrorMessages.USER_NOT_AUTHENTICATED);
			throw new ForbiddenException(ErrorCodes.USER_NOT_AUTHENTICATED, ErrorMessages.USER_NOT_AUTHENTICATED);
		}		
		/*
		 * menuCache.loadMenusOnUser(user);
		 * CacheManager cacheManager = CacheManager.getInstance(); AppConfigurationCache
		 * configCache = cacheManager.getCache(AppConfigurationCache.NAME);
		 * ApplicationMenuLoader menuLoader = new ApplicationMenuLoader(configCache);
		 * LOGGER.debug("Loading menu for the user {}", user.getEmail());
		 * menuLoader.loadMenu(user);
		 */
		user.setMenuRole(getMenuBasedOnUser());
		return user;
	}

	public ApplicationUser getCurrentUser() {
		return getCurrentUser(false);
	}

	public ApplicationUser getCurrentUser(boolean isUserId) {
		return (ApplicationUser) UserContextThreadLocal.getCurrentUserContext();
	}

	public String getMenuBasedOnUser() {
		ApplicationUser userPrivilege = getCurrentUser();
		return menuRoleUtil.menuBasedonRoles(userPrivilege.getUserRoles(), menuCache.query("Menu"));
	}
	
	protected IPerimeterManager<ApplicationUser> getPerimeterManager() {
		return applicationUserPerimeterImpl;
	}
	
	public ApplicationUser getApplicationUserWithEmail(String email) {
		ApplicationUser user = new ApplicationUser();
		user.setEmail(email);
		user.setLogin(true);
		return user;
	}
	
}