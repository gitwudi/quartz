package com.rongyi.platform.quartz.biz;

import java.util.List;

import com.rongyi.platform.quartz.module.dto.QzJobDto;
import com.rongyi.platform.quartz.module.model.QzJob;

public interface QzJobBiz {

	/**
	 * @Description 查询job列表
	 * @param qzJobDto
	 * @return
	 */
	public List<QzJob> list(QzJobDto qzJobDto);

	/**
	 * @Description 根据ID查找job
	 * @param id
	 * @return
	 */
	public QzJob find(Integer id);

	/**
	 * @Description 启动任务
	 * @param qzJob
	 */
	public void add(QzJob qzJob);

	/**
	 * @Description 暂停任务
	 * @param qzJob
	 */
	public void pause(QzJobDto qzJobDto);

	/**
	 * @Description 删除任务
	 * @param qzJob
	 */
	public void delete(QzJobDto qzJobDto);

	/**
	 * @Description 恢复任务
	 * @param qzJob
	 */
	public void resume(QzJobDto qzJobDto);
}
