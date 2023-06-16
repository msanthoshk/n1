package com.a.n.service;

import com.vs.rappit.base.acl.IPerimeterManager;
import com.a.n.base.service.RappitImportErrorBaseService;
import com.a.n.model.RappitImportError;
import com.a.n.service.RappitImportErrorPerimeterImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class RappitImportErrorService extends RappitImportErrorBaseService<RappitImportError> implements IRappitImportErrorService<RappitImportError>{

		@Autowired
		private  RappitImportErrorPerimeterImpl  rappitimporterrorPerimeterImpl;

		public RappitImportErrorService(ChangelogService changelogService) {
		super(RappitImportError.class);	
		setChangelogService(changelogService); 
		
	}
	
	
	protected IPerimeterManager<RappitImportError> getPerimeterManager() {
		return rappitimporterrorPerimeterImpl;
	}
}