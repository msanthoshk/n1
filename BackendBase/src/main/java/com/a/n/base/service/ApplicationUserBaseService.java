package com.a.n.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import com.vs.rappit.base.dal.providers.SearchOptions;
import com.vs.rappit.base.dal.providers.PersistenceType;
import com.vs.rappit.base.logic.BaseBusinessLogic;
import com.vs.rappit.base.mail.model.EmailAddress;
import com.vs.rappit.base.model.wrapper.UserPrivilegePerimeter;
import com.vs.rappit.base.dal.Filter;
import com.vs.rappit.base.dal.SimpleFilter;
import com.vs.rappit.base.dal.Sort;
import com.vs.rappit.base.dal.Sort.Direction;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.vs.rappit.base.authentication.logic.IAppUserPrivilegeCache;
import com.vs.rappit.base.authentication.logic.AppUserPrivilegeCache;
import com.vs.rappit.base.cache.CacheManager;
import com.vs.rappit.base.service.changelog.ChangelogBLBaseImpl;
import com.vs.rappit.base.util.Constants;
import com.vs.rappit.base.exception.AlreadyExistsException;
import com.vs.rappit.base.util.ErrorCode;
import com.vs.rappit.base.exception.ValidationError;

import com.a.n.base.model.ApplicationUserBase;
import com.a.n.base.service.IApplicationUserBaseService;
import com.a.n.model.Roles;
import com.vs.rappit.base.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import com.vs.rappit.base.dal.providers.DBOptions;

import com.vs.rappit.base.dal.providers.DeleteOptions;

public abstract class ApplicationUserBaseService<T extends ApplicationUserBase> extends BaseBusinessLogic<T> implements IApplicationUserBaseService<T>
{
	
	@Autowired
	AppUserPrivilegeCache<T> userCache;
	
	List<Map<String, Object>> userData = JsonUtil.toListMap("[{\"firstName\":\"Ramya\",\"lastName\":\"K\",\"email\":\"ramyakanagaraja96@gmail.com\"},{\"firstName\":\"Shibin\",\"lastName\":\"C\",\"email\":\"cshibin@vanenburg.com\"},{\"firstName\":\"Swetha\",\"lastName\":\"M\",\"email\":\"mswetha@vanenburg.com\"},{\"firstName\":\"s\",\"lastName\":\"s\",\"email\":\"bnk@gmail.com\"},{\"firstName\":\"test\",\"lastName\":\"test\",\"email\":\"testtestvanenburg@gmail.com\"},{\"firstName\":\"Priya\",\"lastName\":\"Dharshini\",\"email\":\"ppriyadharshini@vanenburg.com\"},{\"firstName\":\"TEst USrrdsf\",\"lastName\":\"tef\",\"email\":\"tstr@gmci.com\"},{\"firstName\":\"Kasthuri Rangan\",\"email\":\"rkasthurirangan@vanenburg.com\"},{\"firstName\":\"TEstMurugesh\",\"email\":\"smurugeshsgn@gmail.com\"},{\"firstName\":\"Vinodh\",\"lastName\":\"JK\",\"email\":\"jvinodh@vanenburg.com\"},{\"firstName\":\"Vinsu\",\"lastName\":\"V\",\"email\":\"varghesevinsu1985@gmail.com\"},{\"firstName\":\"gdsfsdf\",\"lastName\":\"fsdfsd\",\"email\":\"sdfds@gm.com\"},{\"firstName\":\"ggg\",\"lastName\":\"eeeg\",\"email\":\"examplerappit@gmail.com\"},{\"firstName\":\"fname\",\"lastName\":\"lname\",\"email\":\"tgkkarthic@gmail.com\"},{\"firstName\":\"SK\",\"lastName\":\"SK\",\"email\":\"rsaravanakumar@vanenburg.com\"},{\"firstName\":\"Hans\",\"lastName\":\"Don\",\"email\":\"hdon@vanenburg.com\"},{\"firstName\":\"asda\",\"lastName\":\"ads\",\"email\":\"asdad@dfsafsd.com\"},{\"firstName\":\"alinta\",\"lastName\":\"Anoop\",\"email\":\"anoop.abm@gmail.com\"},{\"firstName\":\"Paper\",\"lastName\":\"Kraft\",\"email\":\"paperfraft@gmail.com\"},{\"firstName\":\"EXTERNAL Arun\",\"lastName\":\"Test\",\"email\":\"vbtesting7@gmail.com\"},{\"firstName\":\"Gokul\",\"lastName\":\"ramasamy\",\"email\":\"gokulrajarjunan@vanenburg.com\"},{\"firstName\":\"zzz\",\"lastName\":\"tts\",\"email\":\"ak@gmail.com\"},{\"firstName\":\"ss\",\"lastName\":\"llj\",\"email\":\"aaa@gmail.com\"},{\"firstName\":\"wd\",\"lastName\":\"ad\",\"email\":\"ASAC1@gmail.com\"},{\"firstName\":\"ramya\",\"lastName\":\"Test\",\"email\":\"testuser@gmail.com\"},{\"firstName\":\"ss\",\"lastName\":\"zzzr\",\"email\":\"k@gmail.com\"},{\"firstName\":\"Ramya\",\"lastName\":\"K\",\"email\":\"kramya@vanenburg.com\"},{\"firstName\":\"anoop\",\"lastName\":\"abraham\",\"email\":\"aanoop@vanenburg.com\"},{\"firstName\":\"Tamilmani\",\"lastName\":\"M\",\"email\":\"mtamilmani@vanenburg.com\"},{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"test.user@vb.com\"},{\"firstName\":\"Arun Test\",\"lastName\":\"User\",\"email\":\"testingvanenmine@gmail.com\"},{\"firstName\":\"MS\",\"lastName\":\"Dhonie\",\"email\":\"asac@gmail.com\"},{\"firstName\":\"Jithin\",\"lastName\":\"Jacob\",\"email\":\"jjithin@vanenburg.com\"},{\"firstName\":\"testexample\",\"lastName\":\"testing12\",\"email\":\"testrappit@vanenburg.com\"},{\"firstName\":\"Sreekanth\",\"lastName\":\"M\",\"email\":\"msreekanth@vanenburg.com\"},{\"firstName\":\"Gokul\",\"lastName\":\"Arjunan\",\"email\":\"agokulraj@vanenburg.com\"},{\"firstName\":\"zz\",\"lastName\":\"aa\",\"email\":\"qwe@gmail.com\"},{\"firstName\":\"test\",\"lastName\":\"Rishis\",\"email\":\"testrishi1166@gmail.com\"},{\"firstName\":\"Sangeetha\",\"lastName\":\"Selvakumar\",\"email\":\"ssangeetha@vanenburg.com\"},{\"firstName\":\"Dhamodaran\",\"lastName\":\"Perumal\",\"email\":\"pdhamodaran@vanenburg.com\"},{\"firstName\":\"Gokul\",\"lastName\":\"ramasamy\",\"email\":\"gokulrajagr@vanenburg.com\"},{\"firstName\":\"Mustafa\",\"lastName\":\"AbdulNazeer\",\"email\":\"amustafa@vanenburg.com\"},{\"firstName\":\"Kasthurirangan\",\"lastName\":\"Ramesh\",\"email\":\"rkasthurirangan@vanenburg.com\"},{\"firstName\":\"as\",\"lastName\":\"asdad\",\"email\":\"asdad@vg.com\"},{\"firstName\":\"Gokul\",\"lastName\":\"ramasamy\",\"email\":\"agokulrajagr@vanenburg.com\"},{\"firstName\":\"examp123\",\"lastName\":\"excc\",\"email\":\"exampleuser11@gmail.com\"},{\"firstName\":\"Yogeshwar\",\"lastName\":\"kanagaraj\",\"email\":\"kyogeshwar@vanenburg.com\"},{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"test.user1@vb.com\"},{\"firstName\":\"Sampath\",\"lastName\":\"P\",\"email\":\"psampath@vanenburg.com\"},{\"firstName\":\"minato106\",\"lastName\":\"sensei\",\"email\":\"minato106@sensei.com\"},{\"firstName\":\"Ivana\",\"lastName\":\"Anoop\",\"email\":\"ivana@gmail.com\"},{\"firstName\":\"Raina 1511785\",\"lastName\":\"Hope 1231854\",\"email\":\"testuseremail8714@gmail.com\"},{\"firstName\":\"Sarath\",\"lastName\":\"R\",\"email\":\"rsarath@vanenburg.com\"},{\"firstName\":\"TEsdfs\",\"lastName\":\"Trfd\",\"email\":\"gfdg@gdsf.com\"},{\"firstName\":\"Arunkumar\",\"lastName\":\"D\",\"email\":\"darunkumar@vanenburg.com\"},{\"firstName\":\"gg\",\"lastName\":\"hhgg\",\"email\":\"examplerappit1@gmail.com\"},{\"firstName\":\"k\",\"lastName\":\"rishi\",\"email\":\"krishi@vanenburg.com\"},{\"firstName\":\"Angel\",\"lastName\":\"Anoop\",\"email\":\"angelina@gmail.com\"},{\"firstName\":\"AKASH\",\"lastName\":\"Panneer Selvam\",\"email\":\"pakash@vanenburg.com\"},{\"firstName\":\"John'A\",\"lastName\":\"Smith\",\"email\":\"john@yahoo.com\"},{\"firstName\":\"Gokul\",\"lastName\":\"ramasamy\",\"email\":\"gokulrajarjunanagr@vanenburg.com\"},{\"firstName\":\"Sort\",\"lastName\":\"Test\",\"email\":\"sorting@gamil.com\"},{\"firstName\":\"Test F\",\"lastName\":\"Test Ln\",\"email\":\"jack@yaoo.co\"},{\"firstName\":\"Vinsu\",\"lastName\":\"Varghese\",\"email\":\"vvinsu@vanenburg.com\"},{\"firstName\":\"Karthikeyan\",\"lastName\":\"Srinivasan\",\"email\":\"skarthikeyan@vanenburg.com\"},{\"firstName\":\"vinitha\",\"lastName\":\"c\",\"email\":\"cvinitha@vanenburg.com\"},{\"firstName\":\"Shanjeev\",\"lastName\":\"kumar\",\"email\":\"jshanjeevkumar@vanenburg.com\"},{\"firstName\":\"Santhosh\",\"lastName\":\"Kumar\",\"email\":\"msanthoshkumar@vanenburg.com\"},{\"firstName\":\"Ramya\",\"lastName\":\"K\",\"email\":\"ramyakanagaraja98@gmail.com\"},{\"firstName\":\"kl\",\"lastName\":\"rahul\",\"email\":\"kl45@gmail.com\"},{\"firstName\":\"vinitha\",\"lastName\":\"c\",\"email\":\"vinithachandrasekaran@gmail.com\"},{\"firstName\":\"w\",\"lastName\":\"kk\",\"email\":\"w@gmail.com\"},{\"firstName\":\"Sukumaran\",\"lastName\":\"Minato\",\"email\":\"vsukumaran@vanenburg.com\"},{\"firstName\":\"Pavithra\",\"lastName\":\"S\",\"email\":\"spavithra@vanenburg.com\"},{\"firstName\":\"User test\",\"lastName\":\"User test\",\"email\":\"test123@vb.com\"},{\"firstName\":\"c\",\"lastName\":\"abilash\",\"email\":\"abilash131999@gmail.com\"},{\"firstName\":\"example\",\"lastName\":\"exam\",\"email\":\"exa@gmail.com\"},{\"firstName\":\"Gokul\",\"lastName\":\"ramasamy\",\"email\":\"agokulrajarjunan@vanenburg.com\"},{\"firstName\":\"sample12\",\"lastName\":\"sam13\",\"email\":\"sam@gmail.com\"},{\"firstName\":\"Murugesan\",\"lastName\":\"Sathiyamoorthy\",\"email\":\"smurugesan@vanenburg.com\"},{\"firstName\":\"Test\",\"lastName\":\"tes\",\"email\":\"test@gmail.com\"},{\"firstName\":\"Vignesh\",\"lastName\":\"Prabhu\",\"email\":\"rvignesh@vanenburg.com\"},{\"firstName\":\"Abilash\",\"lastName\":\"Chandran\",\"email\":\"cabilash@vanenburg.com\"},{\"firstName\":\"Balaji\",\"lastName\":\"Govindarajulu\",\"email\":\"gbalaji@vanenburg.com\"},{\"firstName\":\"Gokul\",\"lastName\":\"ramasamy\",\"email\":\"gokulrajarjunancs@vanenburg.com\"},{\"firstName\":\"Subitcha\",\"lastName\":\"P\",\"email\":\"subi.chitra98@gmail.com\"},{\"firstName\":\"asf\",\"lastName\":\"asdf\",\"email\":\"asdf@yahoo\"},{\"firstName\":\"Test\",\"lastName\":\"Test\",\"email\":\"test@test.com\"},{\"firstName\":\"Sindhu\",\"lastName\":\"T\",\"email\":\"tsindhu@vanenburg.com\"},{\"firstName\":\"Linta\",\"lastName\":\"Aug\",\"email\":\"lintaatwork@gmail.com\"},{\"firstName\":\"Desk\",\"lastName\":\"Note\",\"email\":\"desknot@gmial.com\"},{\"firstName\":\"Sukumaran\",\"lastName\":\"Minato\",\"email\":\"vsukumaran@gmail.com\"},{\"firstName\":\"Subitcha\",\"lastName\":\"Panneerselvam\",\"email\":\"psubitcha@vanenburgsoftware.com\"},{\"firstName\":\"shdsk\",\"lastName\":\"jdjd\",\"email\":\"sk120909@gmail.com\"},{\"firstName\":\"test\",\"lastName\":\"asd\",\"email\":\"test.aasd@gmail.com\"},{\"firstName\":\"Test\",\"lastName\":\"kumar\",\"email\":\"testkumar664@gmail.com\"},{\"firstName\":\"ee\",\"lastName\":\"ffh\",\"email\":\"a@gmail.com\"},{\"firstName\":\"Dev User sorting Test\",\"lastName\":\"Dhamo\",\"email\":\"dhamo@test.com\"},{\"firstName\":\"a\",\"lastName\":\"linta\",\"email\":\"alinta@vanenburg.com\"},{\"firstName\":\"Julian\",\"lastName\":\"Baan\",\"email\":\"julianbaan@vanenburg.com\"},{\"firstName\":\"Linta\",\"lastName\":\"Augustine\",\"email\":\"linta.aug@gmail.com\"},{\"firstName\":\"Saravanan\",\"lastName\":\"S\",\"email\":\"ssaravanan@vanenburg.com\"},{\"firstName\":\"Gokul\",\"lastName\":\"ramasamy\",\"email\":\"gokulraj@vanenburg.com\"},{\"firstName\":\"Subitcha\",\"lastName\":\"PaneerSelvam\",\"email\":\"psubitcha@vanenburg.com\"},{\"firstName\":\"s\",\"lastName\":\"sk\",\"email\":\"testtestvanenburg@gmail.com\"},{\"firstName\":\"vinsu\",\"lastName\":\"vbtesteva1\",\"email\":\"vbtesteva1@gmail.com\"},{\"firstName\":\"Test\",\"lastName\":\"User2\",\"email\":\"test.user2@vb.com\"}]");
	
	@Override
	public void loadUserData(){
		for(Map<String, Object> user : userData){
			ApplicationUserBase applicationUser = new ApplicationUserBase();
			applicationUser.setFirstName((String)user.get("firstName"));
			applicationUser.setLastName((String)user.get("lastName"));
			applicationUser.setEmail((String)user.get("email"));
			//applicationUser.setEmailInLowerCase(applicationUser.getEmail().toLowerCase());
			applicationUser.setUserRoles(Arrays.asList("Development Administrator"));
			applicationUser.setDevAdmin(true);
			applicationUser.setCreatedBy("generation-rappit@vanenburg.com");
			applicationUser.setCreatedDate(new Date());
			//applicationUser.setFullName(applicationUser.getFirstName()+" "+applicationUser.getLastName());
			save((T) applicationUser);

		}
	}
	
	private ChangelogBLBaseImpl changelogService;
	
	public ApplicationUserBaseService(Class<T> modelClass) {
		super(modelClass);
		addPersistenceOption(DBOptions.DELETE_OPTION, DeleteOptions.PERMANENT_DELETE);
		addPersistenceOption(SearchOptions.SEARCH_INDEX_NAME, getTableName());

	}

	@Override
	public PersistenceType[] getOtherPersistenceTypes() {
		return new PersistenceType[] {PersistenceType.SEARCH};
	}
	
	public final void onBeforeSave(PersistenceType type, T modelObj) {
		switch (type) {
			case DB:
				onBeforeSaveDB(modelObj);
				break;
			case SEARCH:
				onBeforeSaveSearch(modelObj);
				break;			
			default:
				break;
		}
		super.onBeforeSave(type, modelObj);
	}

	public void onBeforeSaveSearch(T modelObj) {}

	public void onBeforeSaveDB(T modelObj) {
		if(modelObj.getEmail() != null) {
			modelObj.setEmailInLowerCase(modelObj.getEmail().toLowerCase());
		}
		isObjectExists(modelObj,false);
		setRoles(modelObj);
	}
	
	@Override
	public final void onAfterSave(PersistenceType type, Object modelObj) {
		super.onAfterSave(type, modelObj);
		switch (type) {
			case DB:
				onAfterSaveDB((T) modelObj);
				break;			
			default:
				break;
		}		
	}

	public void onAfterSaveDB(T modelObj) {
		changelogService.createChangeLog("ApplicationUser", modelObj.getSid().toString(), Constants.SAVED, modelObj);
		invalidateCache(modelObj);
	}

	public final void onBeforeUpdate(PersistenceType type, T modelObj) {
		switch (type) {
			case DB:
				onBeforeUpdateDB(modelObj);
				break;
			case SEARCH:
				onBeforeUpdateSearch(modelObj);
				break;
			default:
				break;
		}
		super.onBeforeUpdate(type, modelObj);
	}

	public void onBeforeUpdateSearch(T modelObj) {}

	public void onBeforeUpdateDB(T modelObj) {
		if(modelObj.getEmail() != null) {
			modelObj.setEmailInLowerCase(modelObj.getEmail().toLowerCase());
		}
		isObjectExists(modelObj,true);
		setRoles(modelObj);
	}
	
	@Override
	public final void onAfterUpdate(PersistenceType type, Object modelObj) {		
		switch (type) {
			case DB:
				onAfterUpdateDB((T)modelObj);
				break;			
			default:
				break;
		}	
		super.onAfterUpdate(type, modelObj);	
	}

	public void onAfterUpdateDB(T modelObj) {
		changelogService.createChangeLog("ApplicationUser", modelObj.getSid().toString(), Constants.UPDATED, modelObj);
		invalidateCache(modelObj);
	}
	
	@Override
	public final void onAfterDelete(PersistenceType type, Object modelObj) {		
		switch (type) {
			case DB:
				onAfterDeleteDB((T)modelObj);
				break;			
			default:
				break;
		}
		super.onAfterDelete(type, modelObj);		
	}

	public void onAfterDeleteDB(T modelObj) {
		changelogService.createChangeLog("ApplicationUser", modelObj.getSid().toString(), Constants.DELETED, modelObj);
		invalidateCache(modelObj);
	}

	private void invalidateCache(T modelObj) {
		userCache.invalidate(modelObj.getEmail());
	}
	
	protected void setRoles(T modelObj) {
		List<String> userRoles = new ArrayList<>();
				if(modelObj.isDevAdmin()){
			userRoles.add(Roles.DEVADMIN.getRoleName());
		}
		modelObj.setUserRoles(userRoles);
	}
	
	public List<EmailAddress> getUsersByRole(UserPrivilegePerimeter rolePerimeterInfo, Integer page, Integer pageSize) {
		List<Filter> filters = new ArrayList<>();
		Map<String, List<Object>> perimeters = rolePerimeterInfo.getPerimeters();
		boolean perimeterApplicable = perimeters != null && perimeters.size() > 0;
		if (perimeterApplicable) {
			perimeters.forEach((perimeterKey, perimeterValue) -> {
				filters.add(new SimpleFilter(perimeterKey, perimeterValue, Filter.Operator.IN));
			});
			if (filters.isEmpty()) {
				return Collections.emptyList();
			}
		}
		filters.add(new SimpleFilter(rolePerimeterInfo.getRoleShortName(), true));
		List<Sort> sorts = new ArrayList<>(1);
		sorts.add(new Sort("email", Direction.ASC));
		List<String> projectedFields = new ArrayList<>(3);
		projectedFields.add("email");
		projectedFields.add("firstName");
		projectedFields.add("lastName");
		List<T> responseList = (List<T>)getAllByPage(PersistenceType.SEARCH, filters, sorts,page, pageSize, projectedFields);
		if (CollectionUtils.isEmpty(responseList)) {
			return Collections.emptyList();
		}
		List<EmailAddress> emailInfoList = new ArrayList<>(responseList.size());
		responseList.forEach(data -> {
			emailInfoList.add(new EmailAddress(data.getEmail(), data.getFirstName()
					+ (StringUtils.isNotBlank(data.getLastName()) ? data.getLastName() : StringUtils.EMPTY)));
		});
		return emailInfoList;
	}
	
	
	
	public void setChangelogService(ChangelogBLBaseImpl changelogService) {
		this.changelogService=changelogService;
	}
	
	
	protected void isObjectExists(T modelObj, boolean isUpdate) {
		T existing = getByField("emailInLowerCase", modelObj.getEmailInLowerCase());
		if (isUpdate) {
			if (existing == null || existing.getSid().equals(modelObj.getSid())) {
				return;
			}
			throw new AlreadyExistsException(ErrorCode.USER_ALREADY_EXISTS, "ApplicationUser", modelObj.getEmail());

		} else {
			if (existing == null) {
				return;
			}
			throw new AlreadyExistsException(ErrorCode.USER_ALREADY_EXISTS, "ApplicationUser", modelObj.getEmail());
		}
	}
	
	@Override
	public List<String> onBeforeGeneratedValidation() {
		return null;
	}

	@Override
	public void onAfterGeneratedValidation(List<ValidationError> validationErrors) {}
}