package com.rongyi.platform.quartz.init;

import java.util.ArrayList;
import java.util.List;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rongyi.platform.quartz.biz.JobBiz;
import com.rongyi.platform.quartz.model.QzJob;

public class LoadJob {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JobBiz jobBiz;

	public void init() throws SchedulerException {
		List<QzJob> jobList = new ArrayList<>();
		QzJob qzJob = new QzJob();
		qzJob.setName("hi");
		qzJob.setGroupName("defaut");
		qzJob.setTriggerName("hiTrigger");
		qzJob.setClassName("com.rongyi.platform.quartz.job.HiJob");
		qzJob.setCronExpression("0/5 * * * * ? ");
		jobList.add(qzJob);
		// 可执行的任务列表
		log.info("初始化加载定时任务......");
		for (QzJob job : jobList) {
			jobBiz.add(job);
		}
	}
}