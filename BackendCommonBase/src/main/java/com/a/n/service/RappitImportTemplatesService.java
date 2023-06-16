package com.a.n.service;

import com.vs.rappit.base.acl.IPerimeterManager;
import com.a.n.base.service.RappitImportTemplatesBaseService;
import com.a.n.model.RappitImportTemplates;
import com.a.n.service.RappitImportTemplatesPerimeterImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class RappitImportTemplatesService extends RappitImportTemplatesBaseService<RappitImportTemplates> implements IRappitImportTemplatesService<RappitImportTemplates>{

		@Autowired
		private  RappitImportTemplatesPerimeterImpl  rappitimporttemplatesPerimeterImpl;

		public RappitImportTemplatesService(ChangelogService changelogService) {
		super(RappitImportTemplates.class);	
		setChangelogService(changelogService); 
		
	}
	
	
	protected IPerimeterManager<RappitImportTemplates> getPerimeterManager() {
		return rappitimporttemplatesPerimeterImpl;
	}
}