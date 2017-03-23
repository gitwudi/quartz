package com.rongyi.platform.quartz.job;

import java.util.Iterator;

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
		Integer num = (Integer) (jobDataMap.get("haha"));
		log.info("num : {}", num);
		context.getJobDetail().getJobDataMap().put("haha", ++num);

		log.info("启动job - {} : {}", "HiJob==========haha", jobDataMap.get("haha").toString());
	}

}
