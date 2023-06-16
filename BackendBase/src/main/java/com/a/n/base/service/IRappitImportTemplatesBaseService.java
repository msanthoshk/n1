package com.a.n.base.service;

import com.vs.rappit.base.logic.ICRUDOperation;
import com.a.n.base.model.RappitImportTemplatesBase;


public interface IRappitImportTemplatesBaseService<T extends RappitImportTemplatesBase> extends ICRUDOperation<T>{
	
	
	public void setUp();
}