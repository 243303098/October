package com.example.controller;

import com.example.model.Project;
import com.example.model.UIElement;
import com.example.service.ProjectService;
import com.example.service.UIElementService;
import com.example.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView toElementManagePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uiTest/elementManage");
        List<UIElement> uiElementList = uiElementService.getAllUIElement();
        modelAndView.addObject("uiElementList",uiElementList);
        return modelAndView;
    }

    /**
     * 根据name查询项目信息
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/uiTest/elementManage", method = RequestMethod.POST)
    public ModelAndView getProjectByName(String name) {
        List<UIElement> uiElementList;
        ModelAndView modelAndView = new ModelAndView();
        if (StringUtil.isNull(name)){
            uiElementList = uiElementService.getAllUIElement();
        }else {
            uiElementList = uiElementService.getUIElementByName(name);
        }
        modelAndView.setViewName("uiTest/elementManage");
        modelAndView.addObject("uiElementList", uiElementList);
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
    public ModelAndView addProject(Integer id, String projectName, String name, String byType, String path, String comment){
        ModelAndView modelAndView = new ModelAndView();
        UIElement uiElement = new UIElement();
        uiElement.setCreatetime(new Date());
        uiElement.setName(name);
        uiElement.setBytype(byType);
        uiElement.setPath(path);
        uiElement.setComment(comment);
        uiElement.setProjectid(projectService.getProjectByName(projectName).get(0).getId());
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
    public ModelAndView getAddProjectPageById(Integer id, String name, String bytype, String path, String comment, String projectId){
        ModelAndView modelAndView = new ModelAndView();
        List<Project> projectList = projectService.getAllProject();
        modelAndView.setViewName("uiTest/elementEdit");
        if (!StringUtil.isNull(projectId)){
            modelAndView.addObject("projectName", projectService.selectByKey(projectId).getName());
        }
        modelAndView.addObject("elementId", id);
        modelAndView.addObject("name", name);
        modelAndView.addObject("bytype", bytype);
        modelAndView.addObject("path", path);
        modelAndView.addObject("comment", comment);
        modelAndView.addObject("projectList",projectList);
        return modelAndView;
    }

}
