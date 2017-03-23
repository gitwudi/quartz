package com.rongyi.platform.quartz.init;

import java.util.List;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongyi.platform.quartz.biz.QzJobBiz;
import com.rongyi.platform.quartz.module.model.QzJob;

public class LoadJob {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QzJobBiz qzJobBiz;
	@Autowired
	private JobService jobService;

	public void init() throws SchedulerException {
		// 可执行的任务列表
		List<QzJob> jobList = qzJobBiz.list(null);
		log.info("初始化加载定时任务......");
		for (QzJob job : jobList) {
			qzJobBiz.add(job);
		}
	}
}