package com.rongyi.platform.quartz.module.vo;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.rongyi.platform.quartz.module.exception.BizException;

public class ResultVo {

	private static final String SUCCESS = "0"; // 成功

	private static final String FAIL = "-1"; // 失败

	private String code;

	private String msg = "";

	private Object data;

	private Page page;

	public ResultVo() {
		this.code = SUCCESS;
	}

	public <T> ResultVo(T data) {
		this.code = SUCCESS;
		this.data = data;
	}

	public ResultVo(String code) {
		this.code = code;
	}

	public ResultVo(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResultVo(String code, Object data) {
		this.code = code;
		this.data = data;
	}

	public ResultVo(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public <T> ResultVo(T data, Integer page, Integer size, Integer totalCount) {
		this.code = SUCCESS;
		this.page = new Page(page, size, totalCount);
		this.data = data;
	}

	public static <T> ResultVo success() {
		return new ResultVo();
	}

	public static <T> ResultVo success(T data) {
		return new ResultVo(data);
	}

	public static <T> ResultVo success(PageList<T> pageList, PageBounds pageBounds) {
		ArrayList<T> list;
		Integer totalCount;
		if (CollectionUtils.isEmpty(pageList) || null == pageList.getPaginator()) {
			totalCount = 0;
			list = new ArrayList<>();
		} else {
			totalCount = pageList.getPaginator().getTotalCount();
			list = pageList;
		}
		return success(list, pageBounds.getPage(), pageBounds.getLimit(), totalCount);
	}

	public static <T> ResultVo success(T data, Integer page, Integer size, Integer totalCount) {
		return new ResultVo(data, page, size, totalCount);
	}

	public static final ResultVo failue() {
		return new ResultVo(FAIL);
	}

	public static final ResultVo failue(String msg) {
		return new ResultVo(FAIL, msg);
	}

	public static final ResultVo failue(BizException e) {
		return new ResultVo(e.getCode(), e.getErrorMsg());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}