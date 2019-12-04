package com.example.controller;

import com.example.model.Project;
import com.example.model.QuartzTask;
import com.example.service.ProjectService;
import com.example.service.QuartzTaskService;
import com.example.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public ModelAndView toSchedulePage(HttpServletRequest request){
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
    public ModelAndView editSchedule(HttpServletRequest request, Integer id, String jobName, String cron, String jobParam, String description){
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
        if (!StringUtil.isNull(String.valueOf(id))){
            quartzTask.setId(id);
            quartzTaskService.updateAll(quartzTask);
        }else {
            quartzTaskService.save(quartzTask);
        }
        modelAndView.setViewName("redirect:/schedule");
        return modelAndView;
    }

    @RequestMapping(value = "/toEditSchedulePage", method = RequestMethod.GET)
    public ModelAndView toEditSchedulePage(Integer id, String jobName, String cron, String jobParam, String description){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id",id);
        modelAndView.addObject("jobName",jobName);
        modelAndView.addObject("cron",cron);
        modelAndView.addObject("jobParam",jobParam);
        modelAndView.addObject("description",description);
        modelAndView.setViewName("uiTest/scheduleEdit");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteSchedule", method = RequestMethod.POST)
    public ModelAndView toEditSchedulePage(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        quartzTaskService.delete(id);
        modelAndView.setViewName("uiTest/scheduleManage");
        return modelAndView;
    }


}
