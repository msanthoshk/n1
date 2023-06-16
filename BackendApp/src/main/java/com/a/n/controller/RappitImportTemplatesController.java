package com.a.n.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.ResponseEntity;
import com.vs.rappit.base.factory.InstanceFactory;
import com.a.n.base.controller.RappitImportTemplatesBaseController;
import com.a.n.service.IRappitImportTemplatesService;
import com.a.n.service.RappitImportTemplatesService;
import com.a.n.model.RappitImportTemplates;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "rest/rappitimporttemplates/", produces = "application/json")
public class RappitImportTemplatesController extends RappitImportTemplatesBaseController<IRappitImportTemplatesService<RappitImportTemplates>, RappitImportTemplates> {
	private static XLogger LOGGER = XLoggerFactory.getXLogger(RappitImportTemplatesController.class);
	public RappitImportTemplatesController(RappitImportTemplatesService rappitimporttemplatesService) {
		super(rappitimporttemplatesService);
	}
}
