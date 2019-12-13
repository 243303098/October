package com.example.controller;

import com.example.model.Project;
import com.example.model.UIModule;
import com.example.model.UIStep;
import com.example.service.ProjectService;
import com.example.service.UIModuleService;
import com.example.service.UIStepService;
import com.example.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/30 17:55
 * @Description:
 */
@RestController
public class UIModuleController {

    @Autowired
    private UIModuleService uiModuleService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UIStepService uiStepService;

    /**
     * 进入module页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/uiTest/moduleManage", method = RequestMethod.GET)
    public ModelAndView toModuleManagePage(HttpServletRequest request){
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        ModelAndView modelAndView = new ModelAndView();
        UIModule uiModule = new UIModule();
        uiModule.setProjectid(projectList.get(0).getId());
        modelAndView.setViewName("uiTest/uiModuleManage");
        modelAndView.addObject("projectId", projectList.get(0).getId());
        modelAndView.addObject("uiModuleList", uiModuleService.getUIModuleBy(uiModule));
        return modelAndView;
    }

    /**
     * 查询module
     * @param moduleName
     * @param request
     * @return
     */
    @RequestMapping(value = "/uiTest/moduleManage", method = RequestMethod.POST)
    public ModelAndView toModuleManagePagePost(@RequestParam(name = "moduleName") String moduleName, HttpServletRequest request){
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        ModelAndView modelAndView = new ModelAndView();
        UIModule uiModule = new UIModule();
        uiModule.setProjectid(projectList.get(0).getId());
        uiModule.setModulename(moduleName);
        modelAndView.setViewName("uiTest/uiModuleManage");
        modelAndView.addObject("projectId", projectList.get(0).getId());
        modelAndView.addObject("uiModuleList", uiModuleService.getUIModuleBy(uiModule));
        return modelAndView;
    }

    /**
     * 新增或更新模块
     * @param id
     * @param moduleName
     * @param status
     * @param comment
     * @param paramData
     * @param returnData
     * @param request
     * @return
     */
    @RequestMapping(value = "/uiTest/editModule", method = RequestMethod.POST)
    public ModelAndView addModule(Integer id, String moduleName, String status, String comment, String paramData, String returnData, HttpServletRequest request){
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        ModelAndView modelAndView = new ModelAndView();
        UIModule uiModule = new UIModule();
        uiModule.setModulename(moduleName);
        uiModule.setComment(comment);
        uiModule.setProjectid(projectList.get(0).getId());
        uiModule.setParamdata(paramData);
        uiModule.setReturndata(returnData);
        uiModule.setStatus(status);
        if (StringUtil.isNull(String.valueOf(id))){
            uiModuleService.save(uiModule);
        }else {
            uiModule.setId(id);
            uiModuleService.updateAll(uiModule);
        }
        //modelAndView.addObject("projectId",projectId);
        modelAndView.setViewName("redirect:/uiTest/moduleManage");
        return modelAndView;
    }

    /**
     * 添加模块页面
     * @param id
     * @param moduleName
     * @param status
     * @param comment
     * @param paramData
     * @param returnData
     * @return
     */
    @RequestMapping(value = "/uiTest/editModule", method = RequestMethod.GET)
    public ModelAndView getAddModulePageById(Integer id, String moduleName, String status, String comment, String paramData, String returnData){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uiTest/uiModuleEdit");
        modelAndView.addObject("id",id);
        modelAndView.addObject("moduleName",moduleName);
        modelAndView.addObject("status",status);
        modelAndView.addObject("comment",comment);
        modelAndView.addObject("paramData",paramData);
        modelAndView.addObject("returnData",returnData);
        return modelAndView;
    }

    /**
     * 删除模块
     * @param id
     * @return
     */
    @RequestMapping(value = "/uiTest/deleteModule", method = RequestMethod.POST)
    public ModelAndView deleteModule(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        //删除模块之前先删除下面的步骤
        uiStepService.deleteBymoduleId(id);
        uiModuleService.delete(id);
        modelAndView.setViewName("uiTest/uiModuleManage");
        return modelAndView;
    }
}
