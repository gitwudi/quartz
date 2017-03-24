package com.rongyi.platform.quartz.init;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongyi.platform.quartz.biz.QzJobBiz;
import com.rongyi.platform.quartz.client.QuartzRMIClient;

public class LoadJob {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QzJobBiz qzJobBiz;
	@Autowired
	private JobService jobService;

	public void init() throws SchedulerException {
		// 可执行的任务列表
//		List<QzJob> jobList = qzJobBiz.list(null);
//		log.info("初始化加载定时任务......");
//		for (QzJob job : jobList) {
//			qzJobBiz.add(job);
//		}
		QuartzRMIClient quartzRMIClient = new QuartzRMIClient();
		try {
			log.info("启动任务");
			quartzRMIClient.start();
		} catch (Exception e) {
			log.error("异常", e);
		}
	}
}