package com.a.n.base.model;

import java.util.HashMap;
import java.util.Map;

public enum DataModelTables {
	APPLICATION_USER("Application User"),
	RAPPIT_US_IMPORT_US_ERROR("rappit_import_error"),
	RAPPIT_US_IMPORT_US_TEMPLATES("rappit_import_templates");

	private static final Map<String, DataModelTables> displayNameMap = new HashMap<String, DataModelTables>();
	static {
		for (DataModelTables displayNameEnum : DataModelTables.values()) {
			displayNameMap.put(displayNameEnum.getDisplayName(), displayNameEnum);
		}
	}
	private String displayName;

	DataModelTables(String displayName) {
		this.displayName = displayName;
	}

	public static DataModelTables getDisplayNameEnum(String displayName) {
		return displayNameMap.get(displayName);
	}

	public String getDisplayName() {
		return displayName;
	}
}
