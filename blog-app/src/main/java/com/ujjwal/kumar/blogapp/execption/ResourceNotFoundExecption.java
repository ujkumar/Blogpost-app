package com.ujjwal.kumar.blogapp.execption;

public class ResourceNotFoundExecption extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldValue;
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}
	public ResourceNotFoundExecption(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s ",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
	
}
