/**
 * 
 */
package com.accolite.aums.dto;

import com.accolite.aums.enums.ResponseType;

/**
 * @author darshan
 *
 */
public class ResponseDto {
	ResponseType responseType;
	int responseCode;
	String msg;
	Object data;
	Object additionalData;

	public ResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(Object additionalData) {
		this.additionalData = additionalData;
	}

}
