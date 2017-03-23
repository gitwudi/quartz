package com.rongyi.platform.quartz.module.enm;

/**
 * 1位保留位固定，2~3位表示终端 ，4~5位表示业务模块，6~8自定义异常
 */
public enum ErrorEnum {

	// =================================== 00-系统异常 =====================================
	SYSTEM_EXCEP("00000001", "系统异常", "请稍等"),
	
	
	;
	
	private String code;
	
	private String errorMsg;
	
	private String showMsg;
	
	ErrorEnum(String code, String errorMsg, String showMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
		this.showMsg = errorMsg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getShowMsg() {
		return showMsg;
	}

	public void setShowMsg(String showMsg) {
		this.showMsg = showMsg; 
	}
	
	
}
