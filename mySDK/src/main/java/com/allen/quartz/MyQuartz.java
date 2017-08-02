package com.allen.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyQuartz {

	private Scheduler scheduler;

	public void init() throws Exception {
		// get a scheduler by factory
		SchedulerFactory schedulerfactory = new StdSchedulerFactory();
		scheduler = schedulerfactory.getScheduler();

		// create a instance of jobDetail
		// declare job's name, group and class
		JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("getConfig", "config").build();

		// use simpleTrigger rule(start every n seconds)
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(60 * 5)).startNow().build();
		
		// use cornTrigger in 10:42 everyday
		// Trigger
		// trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger",
		// "triggerGroup")
		// .withSchedule(CronScheduleBuilder.cronSchedule("0 42 10 * * ? *"))
		// .startNow().build();

		// register the job and trigger in schedule
		scheduler.scheduleJob(job, trigger);

	}
	
	public void execute() throws Exception {
		scheduler.start();
	}
	
	public void shutdown() throws Exception {
		scheduler.shutdown();
	}
}
