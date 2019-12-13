package com.example.controller;

import com.example.model.ExcuteLog;
import com.example.model.Project;
import com.example.service.CaseLogDetailService;
import com.example.service.ExcuteLogService;
import com.example.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/12/11 14:20
 * @Description:
 */

@RestController
public class ExcuteLogController {

    @Autowired
    private ExcuteLogService excuteLogService;

    @Autowired
    private ProjectService projectService;

    @PostConstruct
    public void init() {
        ExcuteLogController.getInstance().excuteLogService = this.excuteLogService;
    }

    private static class SingletonHolder {
        private static final ExcuteLogController INSTANCE = new ExcuteLogController();
    }

    private ExcuteLogController() {
    }

    public static final ExcuteLogController getInstance() {
        return ExcuteLogController.SingletonHolder.INSTANCE;
    }

    /**
     * 实现单例 end
     */
    public ExcuteLogService getExcuteLogService() {
        return ExcuteLogController.getInstance().excuteLogService;
    }

    @RequestMapping(value = "/uiTest/excuteLog", method = RequestMethod.GET)
    public ModelAndView toLogPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        ExcuteLog excuteLog = new ExcuteLog();
        excuteLog.setProjectid(projectList.get(0).getId());
        List<ExcuteLog> excuteLogList = excuteLogService.selectBy(excuteLog);
        //查询caseId在里面的所有log
        modelAndView.addObject("excuteLogList", excuteLogList);
        modelAndView.setViewName("uiTest/uiExcuteLog");
        return modelAndView;
    }

}
