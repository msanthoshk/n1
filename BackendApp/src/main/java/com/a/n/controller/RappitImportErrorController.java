package com.a.n.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.ResponseEntity;
import com.vs.rappit.base.factory.InstanceFactory;
import com.a.n.base.controller.RappitImportErrorBaseController;
import com.a.n.service.IRappitImportErrorService;
import com.a.n.service.RappitImportErrorService;
import com.a.n.model.RappitImportError;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "rest/rappitimporterrors/", produces = "application/json")
public class RappitImportErrorController extends RappitImportErrorBaseController<IRappitImportErrorService<RappitImportError>, RappitImportError> {
	private static XLogger LOGGER = XLoggerFactory.getXLogger(RappitImportErrorController.class);
	public RappitImportErrorController(RappitImportErrorService rappitimporterrorService) {
		super(rappitimporterrorService);
	}
}
