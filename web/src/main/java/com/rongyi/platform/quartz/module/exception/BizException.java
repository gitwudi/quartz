package com.rongyi.platform.quartz.module.exception;

import java.text.MessageFormat;

import com.rongyi.platform.quartz.module.enm.ErrorEnum;

public class BizException extends RuntimeException {
	
	private static final long serialVersionUID = -7530600167685517301L;

	private String errorMsg;
	
	private String code;
	
	private String showMsg;

	public BizException(ErrorEnum errorEnum) {
		this.code = errorEnum.getCode();
		this.errorMsg = errorEnum.getErrorMsg();
		this.showMsg = errorEnum.getShowMsg();
	}
	
	public BizException(ErrorEnum errorEnum, Object... msg) {
		this.code = errorEnum.getCode();
		this.errorMsg = MessageFormat.format(errorEnum.getErrorMsg(), msg);
		this.showMsg = errorEnum.getShowMsg();
	}
	
	public BizException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public BizException(String showMsg, String errorMsg) {
		this.showMsg = showMsg;
	}
	
	public String getErrorMsg() {
		return this.errorMsg;
	}

	public BizException() {
		super();
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShowMsg() {
		return showMsg;
	}

	public void setShowMsg(String showMsg) {
		this.showMsg = showMsg;
	}
}