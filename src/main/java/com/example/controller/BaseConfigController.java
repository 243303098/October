package com.example.controller;

import com.example.model.Project;
import com.example.service.ProjectService;
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
 * @Date: 2019/10/24 16:45
 * @Description:
 */
@RestController
public class BaseConfigController {

    @Autowired
    private ProjectService projectService;

    /**
     * 查询所有的项目信息，带分页
     * @return
     */
    @RequestMapping(value = "/baseConfig/projectManage", method = RequestMethod.GET)
    public ModelAndView projectManager(HttpServletRequest request) {
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("baseConfig/projectManage");
        modelAndView.addObject("project", projectList);
        return modelAndView;
    }

    /**
     * 根据name查询项目信息
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/baseConfig/projectManage", method = RequestMethod.POST)
    public ModelAndView getProjectByName(String name) {
        List<Project> projectList;
        ModelAndView modelAndView = new ModelAndView();
        if (StringUtil.isNull(name)){
            projectList = projectService.getAllProject();
        }else {
            projectList = projectService.getProjectByName(name);
        }
        modelAndView.setViewName("baseConfig/projectManage");
        modelAndView.addObject("project", projectList);
        return modelAndView;
    }

    /**
     * 删除项目
     * @param id
     * @return
     */
    @RequestMapping(value = "/baseConfig/deleteProject", method = RequestMethod.POST)
    public ModelAndView deleteProjectById(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        projectService.delete(id);
        modelAndView.setViewName("baseConfig/projectManage");
        return modelAndView;
    }

    /**
     * 新增项目
     * @param
     * @return
     */
    @RequestMapping(value = "/baseConfig/addProject", method = RequestMethod.POST)
    public ModelAndView deleteProjectById(Integer id, String name, Integer status){
        ModelAndView modelAndView = new ModelAndView();
        Project project = new Project();
        project.setCreatetime(new Date());
        project.setUpdatetime(new Date());
        project.setName(name);
        project.setStatus(status);
        if (StringUtil.isNull(String.valueOf(id))){
            projectService.save(project);
        }else {
            project.setId(id);
            projectService.updateAll(project);
        }
        modelAndView.setViewName("redirect:/baseConfig/projectManage");

        return modelAndView;
    }

    /**
     * 新增页面
     * @return
     */
    @RequestMapping(value = "/baseConfig/projectEdit", method = RequestMethod.GET)
    public ModelAndView getAddProjectPageById(Integer id, String name, Integer status){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("baseConfig/projectEdit");
        modelAndView.addObject("projectId", id);
        modelAndView.addObject("name", name);
        modelAndView.addObject("status", status);
        return modelAndView;
    }

}
