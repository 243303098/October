package com.example.controller;

import com.example.model.Project;
import com.example.model.UICase;
import com.example.service.CaseLogService;
import com.example.service.ProjectService;
import com.example.service.UICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
 * @Date: 2019/11/27 21:40
 * @Description:
 */
@RestController
@Component
public class CaseLogController {

    @Autowired
    private CaseLogService caseLogService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UICaseService uiCaseService;

    @PostConstruct
    public void init() {
        CaseLogController.getInstance().caseLogService = this.caseLogService;
    }

    private static class SingletonHolder {
        private static final CaseLogController INSTANCE = new CaseLogController();
    }

    private CaseLogController() {
    }

    public static final CaseLogController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 实现单例 end
     */
    public CaseLogService getCaseLogService() {
        return CaseLogController.getInstance().caseLogService;
    }


    //@RequestMapping(value = "/uiTest/excuteLog", method = RequestMethod.GET)
    public ModelAndView toLogPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        UICase uiCase = new UICase();
        uiCase.setProjectid(projectList.get(0).getId());
        List<UICase> uiCaseList = uiCaseService.getUICaseBy(uiCase);
        List<Integer> uiCaseId = new ArrayList<>();
        for (int i = 0; i < uiCaseList.size(); i++) {

            uiCaseId.add(uiCaseList.get(i).getId());
        }
        //查询caseId在里面的所有log
        modelAndView.addObject("caseLogList", caseLogService.selectByIDIn(uiCaseId));
        modelAndView.setViewName("uiTest/uiExcuteLog");
        return modelAndView;
    }

}
