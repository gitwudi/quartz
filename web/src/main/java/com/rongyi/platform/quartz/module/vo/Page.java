package com.rongyi.platform.quartz.module.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author wudi
 * @date 2016年7月11日 下午2:05:47
 */
public class Page {

	private Integer page;// 当前页：统一从1开始

	private Integer size = 10; // 每页行数

	private Integer totalCount; // 总行数

	public Page() {

	}

	public Page(Integer page, Integer size, Integer totalCount) {
		this.page = page;
		this.size = size;
		this.totalCount = totalCount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
