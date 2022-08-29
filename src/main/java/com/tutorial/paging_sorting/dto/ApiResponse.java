package com.tutorial.paging_sorting.dto;

public class ApiResponse<T> {

	int recordCount;
	T response;
	public ApiResponse(int recordCount, T response) {
		super();
		this.recordCount = recordCount;
		this.response = response;
	}
	public ApiResponse() {
		super();
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	
	
}
