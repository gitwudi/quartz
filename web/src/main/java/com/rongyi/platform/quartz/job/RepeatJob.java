package com.rongyi.platform.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
public class RepeatJob implements Job {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("启动job - {}", "RepeatJob");
	}
}
