package com.rongyi.platform.quartz.client;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rongyi.platform.quartz.model.QzJob;

public class QuartzRMIClient {

	public void run() throws Exception {

		Logger log = LoggerFactory.getLogger(this.getClass());

		// Use this properties file instead of quartz.properties
		System.setProperty("org.quartz.properties", "config/client.properties");

		// Get a reference to the remote scheduler
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// Define the job to add
//		JobDetail job = new JobDetail("remotelyAddedJob", "default", SimpleJob.class);
//		JobDataMap map = new JobDataMap();
//		map.put("msg", "Your remotely added job has executed!");
//		job.setJobDataMap(map);
//		CronTrigger trigger = new CronTrigger("remotelyAddedTrigger", "default", "remotelyAddedJob", "default",
//				new Date(), null, "/5 * * ? * *");

		QzJob qzJob = new QzJob();
		qzJob.setName("hi");
		qzJob.setGroupName("defaut");
		qzJob.setTriggerName("hiTrigger");
		qzJob.setClassName("com.rongyi.platform.quartz.job.HiJob");
		qzJob.setCronExpression("0/5 * * * * ? ");
		
		TriggerKey triggerKey = TriggerKey.triggerKey(qzJob.getName(), qzJob.getGroupName());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		
		Class clazz = Class.forName(qzJob.getClassName());

		JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(qzJob.getName(), qzJob.getGroupName())
				.build();
		 jobDetail.getJobDataMap().put("haha", 1);// 启动定时器时传参

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(qzJob.getCronExpression());
		// trigger = TriggerBuilder.newTrigger().withIdentity(qzJob.getName(), qzJob.getGroupName())
		// .withSchedule(scheduleBuilder).build();// 启动会立即执行
		
		trigger = TriggerBuilder.newTrigger().withIdentity(qzJob.getName(), qzJob.getGroupName())
				.withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing()).build();// 启动会在下次触发时执行
		// schedule the remote job
		scheduler.scheduleJob(jobDetail, trigger);

		log.info("Remote job scheduled.");
	}

	public static void main(String[] args) throws Exception {
		QuartzRMIClient example = new QuartzRMIClient();
		example.run();
	}
}
