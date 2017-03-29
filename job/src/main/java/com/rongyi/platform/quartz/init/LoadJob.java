package com.rongyi.platform.quartz.init;

import java.util.Date;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.rongyi.platform.quartz.biz.JobBiz;

public class LoadJob {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JobBiz jobBiz;
	
	 @Autowired
	 private SchedulerFactoryBean schedulerFactoryBean;

	public void init() throws SchedulerException {
//		List<QzJob> jobList = new ArrayList<>();
//		QzJob qzJob = new QzJob();
//		qzJob.setName("hi");
//		qzJob.setGroupName("defaut");
//		qzJob.setTriggerName("hiTrigger");
//		qzJob.setClassName("com.rongyi.platform.quartz.job.HiJob");
//		qzJob.setCronExpression("0/5 * * * * ? ");
//		jobList.add(qzJob);
//		// 可执行的任务列表
//		log.info("初始化加载定时任务......");
//		for (QzJob job : jobList) {
//			jobBiz.add(job);
//		}
		
		System.setProperty("org.quartz.properties", "config/server.properties");
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		System.out.println(System.getProperty("org.quartz.properties"));
		// RMI with Quartz requires a special security manager
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new java.rmi.RMISecurityManager());
//		}
		scheduler.start();
		log.info("Quartz RMI Server started at " + new Date());
		log.info("RMI Clients may now access it. ");

	}
}