package com.a.n.base.model;
import com.vs.rappit.base.model.BaseModel;
import com.vs.rappit.base.annotations.Table;
import java.util.List;
import com.vs.rappit.base.annotations.Searchable;


@Table(name="RappitImportTemplates", keys={"sid"})
public class RappitImportTemplatesBase extends BaseModel {

	@Searchable(index = true)
	private String tableId;
	@Searchable(index = true)
	private String templateGcsFileName;
	@Searchable(index = true)
	private String sourceTemplateName;
	@Searchable(index = true)
	private String tableNameTechnical;
	@Searchable(index = true)
	private List<String> sourceTemplateType;
	@Searchable(index = true)
	private Boolean isOld;
	@Searchable(index = true)
	private String tableName;

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTemplateGcsFileName(String templateGcsFileName) {
		this.templateGcsFileName = templateGcsFileName;
	}

	public String getTemplateGcsFileName() {
		return templateGcsFileName;
	}

	public void setSourceTemplateName(String sourceTemplateName) {
		this.sourceTemplateName = sourceTemplateName;
	}

	public String getSourceTemplateName() {
		return sourceTemplateName;
	}

	public void setTableNameTechnical(String tableNameTechnical) {
		this.tableNameTechnical = tableNameTechnical;
	}

	public String getTableNameTechnical() {
		return tableNameTechnical;
	}

	public void setSourceTemplateType(List<String> sourceTemplateType) {
		this.sourceTemplateType = sourceTemplateType;
	}

	public List<String> getSourceTemplateType() {
		return sourceTemplateType;
	}

	public void setIsOld(Boolean isOld) {
		this.isOld = isOld;
	}

	public Boolean isIsOld() {
		return isOld;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}



}