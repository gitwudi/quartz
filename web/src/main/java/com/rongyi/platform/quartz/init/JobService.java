package com.rongyi.platform.quartz.init;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.rongyi.platform.quartz.module.model.QzJob;

@Service
public class JobService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * @Description 启动任务
	 * @author wudi
	 * @param qzJob
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void add(QzJob qzJob) {
		log.info("新增job - {}", qzJob);
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			TriggerKey triggerKey = TriggerKey.triggerKey(qzJob.getName(), qzJob.getGroupName());

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			if (null == trigger) {
				Class clazz = Class.forName(qzJob.getClassName());

				JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(qzJob.getName(), qzJob.getGroupName())
						.build();

				// jobDetail.getJobDataMap().put("haha", 666);// 启动定时器时传参

				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(qzJob.getCronExpression());

				trigger = TriggerBuilder.newTrigger().withIdentity(qzJob.getName(), qzJob.getGroupName())
						.withSchedule(scheduleBuilder).build();

				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(qzJob.getCronExpression());
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				// 按新的trigger重新设置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}

		} catch (Exception e) {
			log.error("=========定时器初始化失败=========  qzJob - " + qzJob.toString(), e);
		}
	}

	/**
	 * @Description 暂停任务
	 * @author wudi
	 */
	public void pause(QzJob qzJob) {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(qzJob.getName(), qzJob.getGroupName());
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			log.error("暂停任务异常", e);
		}
	}

	/**
	 * @Description 删除任务
	 * @author wudi
	 * @param qzJob
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

}
