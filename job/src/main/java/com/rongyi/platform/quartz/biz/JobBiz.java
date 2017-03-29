package com.rongyi.platform.quartz.biz;

import com.rongyi.platform.quartz.model.QzJob;

public interface JobBiz {

	/**
	 * @Description 启动任务
	 * @param qzJob
	 */
	public void add(QzJob qzJob);

	/**
	 * @Description 暂停任务
	 * @param qzJob
	 */
	public void pause(QzJob qzJob);

	/**
	 * @Description 删除任务
	 * @param qzJob
	 */
	public void delete(QzJob qzJob);

	/**
	 * @Description 恢复任务
	 * @param qzJob
	 */
	public void resume(QzJob qzJob);
}
