package com.example.controller;

import com.example.model.Project;
import com.example.model.UIElement;
import com.example.service.ProjectService;
import com.example.service.UIElementService;
import com.example.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/28 11:28
 * @Description:
 */

@RestController
public class UIElementController {

    @Autowired
    private UIElementService uiElementService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/uiTest/elementManage", method = RequestMethod.GET)
    public ModelAndView toElementManagePage(HttpServletRequest request){
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        ModelAndView modelAndView = new ModelAndView();
        UIElement uiElement = new UIElement();
        uiElement.setProjectid(projectList.get(0).getId());
        modelAndView.setViewName("uiTest/elementManage");
        List<UIElement> uiElementList = uiElementService.getUIElementBy(uiElement);
        modelAndView.addObject("uiElementList",uiElementList);
        modelAndView.addObject("projectId",projectList.get(0).getId());
        modelAndView.addObject("projectName", projectList.get(0).getName());
        return modelAndView;
    }

    /**
     * 根据name查询项目信息
     *
     * @param request
     * @param name
     * @return
     */
    @RequestMapping(value = "/uiTest/elementManage", method = RequestMethod.POST)
    public ModelAndView getProjectByName(@RequestParam(name = "name") String name, HttpServletRequest request) {
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        List<UIElement> uiElementList;
        ModelAndView modelAndView = new ModelAndView();
        UIElement uiElement = new UIElement();
        uiElement.setProjectid(projectList.get(0).getId());
        if (!StringUtil.isNull(name)){
            uiElement.setName(name);
        }
        uiElementList = uiElementService.getUIElementBy(uiElement);
        modelAndView.setViewName("uiTest/elementManage");
        modelAndView.addObject("uiElementList", uiElementList);
        modelAndView.addObject("projectName", projectList.get(0).getName());
        return modelAndView;
    }

    /**
     * 删除项目
     * @param id
     * @return
     */
    @RequestMapping(value = "/uiTest/deleteElement", method = RequestMethod.POST)
    public ModelAndView deleteProjectById(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        uiElementService.delete(id);
        modelAndView.setViewName("uiTest/elementManage");
        return modelAndView;
    }

    /**
     * 新增项目
     * @param
     * @return
     */
    @RequestMapping(value = "/uiTest/addElement", method = RequestMethod.POST)
    public ModelAndView addProject(Integer id, String name, String byType, String path, String comment, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        UIElement uiElement = new UIElement();
        uiElement.setCreatetime(new Date());
        uiElement.setName(name);
        if (StringUtil.isNull(byType)){
            uiElement.setBytype("xpath");
        }else {
            uiElement.setBytype(byType);
        }
        uiElement.setPath(path);
        uiElement.setComment(comment);
        uiElement.setProjectid(projectList.get(0).getId());
        if (StringUtil.isNull(String.valueOf(id))){
            uiElementService.save(uiElement);
        }else {
            uiElement.setId(id);
            uiElementService.updateAll(uiElement);
        }
        modelAndView.setViewName("redirect:/uiTest/elementManage");

        return modelAndView;
    }

    /**
     * 新增页面
     * @return
     */
    @RequestMapping(value = "/uiTest/addElement", method = RequestMethod.GET)
    public ModelAndView getAddProjectPageById(Integer id, String name, String bytype, String path, String comment){
        ModelAndView modelAndView = new ModelAndView();
        List<Project> projectList = projectService.getAllProject();
        modelAndView.setViewName("uiTest/elementEdit");
        modelAndView.addObject("elementId", id);
        modelAndView.addObject("name", name);
        modelAndView.addObject("bytype", bytype);
        modelAndView.addObject("path", path);
        modelAndView.addObject("comment", comment);
        modelAndView.addObject("projectList",projectList);
        return modelAndView;
    }

}
