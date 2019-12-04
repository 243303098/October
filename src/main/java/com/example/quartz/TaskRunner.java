package com.example.quartz;

import com.example.model.QuartzTask;
import com.example.service.QuartzTaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化一个测试Demo任务
 * 创建者 科帮网
 * 创建时间	2018年4月3日
 */
@Component
public class TaskRunner implements ApplicationRunner {
    
	private final static Logger LOGGER = LoggerFactory.getLogger(TaskRunner.class);
	
	@Autowired
    private QuartzTaskService quartzTaskService;

	@Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void run(ApplicationArguments var) throws Exception{
    	//Long count = jobService.listQuartzEntity(null);
		List<QuartzTask> quartzTaskList = quartzTaskService.getQuartzTask(null);
		for (int i = 0; i < quartzTaskList.size(); i++) {
			LOGGER.info("初始化测试任务");
			QuartzTask quartz = quartzTaskList.get(i);
			Class cls = Class.forName(quartz.getJobclassname()) ;
			cls.newInstance();
			//构建job信息
			JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobname(),
					quartz.getJobproject())
					.withDescription(quartz.getDescription())
					.usingJobData("jobParam",quartz.getJobparam())
					.build();
			// 触发时间点
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronexpression());
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+quartz.getJobname(), quartz.getJobproject())
					.startNow().withSchedule(cronScheduleBuilder).build();
			//交由Scheduler安排触发
			scheduler.scheduleJob(job, trigger);
		}
    }

}