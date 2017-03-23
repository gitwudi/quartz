package com.rongyi.platform.quartz.biz.impl;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.rongyi.platform.quartz.biz.JobBiz;
import com.rongyi.platform.quartz.module.model.QzJob;

@Service
public class JobBizImpl implements JobBiz {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * @see com.rongyi.platform.quartz.biz.JobBiz#add(com.rongyi.platform.quartz.module.model.QzJob)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void add(QzJob qzJob) {
		log.info("新增job - {}", qzJob);
		try {
			// Scheduler scheduler = schedulerFactoryBean.getScheduler();// 无需调用scheduler.start(),就能启动
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();// 需要调用scheduler.start()才能启动

			TriggerKey triggerKey = TriggerKey.triggerKey(qzJob.getName(), qzJob.getGroupName());

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			if (null == trigger) {
				Class clazz = Class.forName(qzJob.getClassName());

				JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(qzJob.getName(), qzJob.getGroupName())
						.build();

				// jobDetail.getJobDataMap().put("haha", 666);// 启动定时器时传参

				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(qzJob.getCronExpression());

				// trigger = TriggerBuilder.newTrigger().withIdentity(qzJob.getName(), qzJob.getGroupName())
				// .withSchedule(scheduleBuilder).build();// 启动会立即执行
				trigger = TriggerBuilder.newTrigger().withIdentity(qzJob.getName(), qzJob.getGroupName())
						.withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing()).build();// 启动会在下次触发时执行

				scheduler.scheduleJob(jobDetail, trigger);

				scheduler.start();
			} else {
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(qzJob.getCronExpression());
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
						.withSchedule(scheduleBuilder.withMisfireHandlingInstructionDoNothing()).build();
				// 按新的trigger重新设置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}

		} catch (Exception e) {
			log.error("=========定时器初始化失败=========  qzJob - " + qzJob.toString(), e);
		}
	}

	/**
	 * @see com.rongyi.platform.quartz.biz.JobBiz#pause(com.rongyi.platform.quartz.module.model.QzJob)
	 */
	public void pause(QzJob qzJob) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(qzJob.getName(), qzJob.getGroupName());
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			log.error("暂停任务异常", e);
		}
	}

	/**
	 * @see com.rongyi.platform.quartz.biz.JobBiz#delete(com.rongyi.platform.quartz.module.model.QzJob)
	 */
	public void delete(QzJob qzJob) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(qzJob.getName(), qzJob.getGroupName());
		try {
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			log.error("删除任务异常", e);
		}
	}

	/**
	 * @see com.rongyi.platform.quartz.biz.JobBiz#resume(com.rongyi.platform.quartz.module.model.QzJob)
	 */
	public void resume(QzJob qzJob) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(qzJob.getName(), qzJob.getGroupName());
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(qzJob.getName(), qzJob.getGroupName());
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			log.error("恢复任务异常", e);
		}
	}

}
