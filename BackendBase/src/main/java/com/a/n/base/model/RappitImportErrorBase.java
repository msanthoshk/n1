package com.a.n.base.model;
import com.vs.rappit.base.model.BaseModel;
import com.vs.rappit.base.annotations.Table;
import java.util.List;
import com.vs.rappit.base.annotations.Searchable;
import com.vs.rappit.base.exception.ValidationError;


@Table(name="RappitImportError", keys={"sid"})
public class RappitImportErrorBase extends BaseModel {

	@Searchable(index = true)
	private String failedRow;
	@Searchable(index = true)
	private List<ValidationError> validationErrors;
	@Searchable(index = true)
	private String importId;
	@Searchable(index = true)
	private Long rowNumber;
	@Searchable(index = true)
	private String errorMessage;

	public void setFailedRow(String failedRow) {
		this.failedRow = failedRow;
	}

	public String getFailedRow() {
		return failedRow;
	}

	public void setValidationErrors(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

	public void setImportId(String importId) {
		this.importId = importId;
	}

	public String getImportId() {
		return importId;
	}

	public void setRowNumber(Long rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Long getRowNumber() {
		return rowNumber;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}



}