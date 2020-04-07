package com.example.controller;

import com.example.model.Project;
import com.example.model.QuartzTask;
import com.example.quartz.TaskRunner;
import com.example.service.ProjectService;
import com.example.service.QuartzTaskService;
import com.example.tools.StringUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/2 20:58
 * @Description:
 */
@RestController
public class ScheduleController {

    @Autowired
    private QuartzTaskService quartzTaskService;

    @Autowired
    private ProjectService projectService;

    private final static Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public ModelAndView toSchedulePage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        QuartzTask quartzTask = new QuartzTask();
        quartzTask.setJobproject(projectList.get(0).getId().toString());
        modelAndView.addObject("quartzTaskList", quartzTaskService.getQuartzTask(quartzTask));
        modelAndView.setViewName("uiTest/scheduleManage");
        return modelAndView;
    }

    @RequestMapping(value = "/editSchedule", method = RequestMethod.POST)
    public ModelAndView editSchedule(HttpServletRequest request, Integer id, String jobName, String cron, String jobParam, String description) {
        ModelAndView modelAndView = new ModelAndView();
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        QuartzTask quartzTask = new QuartzTask();
        quartzTask.setJobname(jobName);
        quartzTask.setCronexpression(cron);
        quartzTask.setJobparam(jobParam);
        quartzTask.setDescription(description);
        quartzTask.setJobproject(projectList.get(0).getId().toString());
        quartzTask.setJobclassname("com.example.quartz.SchedulerJob");
        quartzTask.setUpdatedata(new Date());
        if (!StringUtil.isNull(String.valueOf(id))) {
            quartzTask.setId(id);
            quartzTaskService.updateAll(quartzTask);
        } else {
            quartzTaskService.save(quartzTask);
        }
        modelAndView.setViewName("redirect:/schedule");
        return modelAndView;
    }

    @RequestMapping(value = "/toEditSchedulePage", method = RequestMethod.GET)
    public ModelAndView toEditSchedulePage(Integer id, String jobName, String cron, String jobParam, String description) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        modelAndView.addObject("jobName", jobName);
        modelAndView.addObject("cron", cron);
        modelAndView.addObject("jobParam", jobParam);
        modelAndView.addObject("description", description);
        modelAndView.setViewName("uiTest/scheduleEdit");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteSchedule", method = RequestMethod.POST)
    public ModelAndView toEditSchedulePage(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        quartzTaskService.delete(id);
        modelAndView.setViewName("uiTest/scheduleManage");
        return modelAndView;
    }

    @RequestMapping(value = "/refreshSchedule", method = RequestMethod.GET)
    public ModelAndView refreshSchedule(HttpServletRequest request) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SchedulerException {
        List<QuartzTask> quartzTaskList = quartzTaskService.getQuartzTask(null);
        scheduler.clear();
        for (int i = 0; i < quartzTaskList.size(); i++) {
            LOGGER.info("初始化测试任务");
            QuartzTask quartz = quartzTaskList.get(i);
            Class cls = Class.forName(quartz.getJobclassname());
            cls.newInstance();
            //构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobname(),
                    quartz.getJobproject())
                    .withDescription(quartz.getDescription())
                    .usingJobData("jobParam", quartz.getJobparam())
                    .build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronexpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobname(), quartz.getJobproject())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
        }
        return this.toSchedulePage(request);
    }
}
