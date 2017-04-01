package com.rongyi.platform.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class HiJob implements Job {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {
		// JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		// JobDataMap jobDataMap = context.getTrigger().getJobDataMap();
		JobDataMap jobDataMap = context.getMergedJobDataMap();

		// Iterator<String> iterator = jobDataMap.keySet().iterator();
		// while (iterator.hasNext()) {
		// log.info("key : {}", jobDataMap.get(iterator.next()));
		// }
		String str = jobDataMap.get("haha").toString();
		Integer num = Integer.valueOf(str);
		context.getJobDetail().getJobDataMap().put("haha", String.valueOf((num + 1)));

		log.info("启动job - {} : before - {}, after - {}", "HiJob==========haha", num, context.getJobDetail().getJobDataMap().get("haha").toString());
	}

}
