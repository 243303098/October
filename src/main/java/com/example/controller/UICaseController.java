package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.model.Project;
import com.example.model.UICase;
import com.example.model.UIModule;
import com.example.model.UIStep;
import com.example.rabbitmq.ConsumeMq;
import com.example.service.ProjectService;
import com.example.service.UICaseService;
import com.example.service.UIModuleService;
import com.example.service.UIStepService;
import com.example.tools.StringUtil;
import kotlin.jvm.Synchronized;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/15 14:07
 * @Description:
 */

@RestController
public class UICaseController {

    @Autowired
    private UICaseService uiCaseService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UIModuleService uiModuleService;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private UIStepService uiStepService;

    private static Object dateMap[][];


    @RequestMapping(value = "/uiTest/caseManage", method = RequestMethod.GET)
    public ModelAndView toModuleManagePage(HttpServletRequest request) {
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        ModelAndView modelAndView = new ModelAndView();
        UICase uiCase = new UICase();
        uiCase.setProjectid(projectList.get(0).getId());
        modelAndView.setViewName("uiTest/uiCaseManage");
        modelAndView.addObject("projectId", projectList.get(0).getId());
        modelAndView.addObject("uiCaseList", uiCaseService.getUICaseBy(uiCase));
        return modelAndView;
    }

    /**
     * 查询module
     *
     * @param name
     * @param request
     * @return
     */
    @RequestMapping(value = "/uiTest/caseManage", method = RequestMethod.POST)
    public ModelAndView toModuleManagePagePost(@RequestParam(name = "name") String name, HttpServletRequest request) {
        Project project = new Project();
        HttpSession session = request.getSession();
        Integer userId = Integer.valueOf(session.getAttribute("user").toString());
        project.setUserid(userId);
        List<Project> projectList = projectService.getProjectBy(project);
        ModelAndView modelAndView = new ModelAndView();
        UICase uiCase = new UICase();
        uiCase.setProjectid(projectList.get(0).getId());
        uiCase.setName(name);
        modelAndView.setViewName("uiTest/uiCaseManage");
        modelAndView.addObject("projectId", projectList.get(0).getId());
        modelAndView.addObject("uiCaseList", uiCaseService.getUICaseBy(uiCase));
        return modelAndView;
    }

    /**
     * 编辑Case页面
     *
     * @param id
     * @param name
     * @param status
     * @param moduleId
     * @return
     */
    @RequestMapping(value = "/uiTest/editCase", method = RequestMethod.GET)
    public ModelAndView getEditCasePageById(Integer id, String name, String moduleId, String status, Integer projectId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uiTest/uiCaseEdit");
        UIModule uiModule = new UIModule();
        uiModule.setProjectid(projectId);
        List<Integer> moduleIdList = new ArrayList();
        List<UIModule> useredModuleList = new ArrayList<>();
        if (!StringUtil.isNull(moduleId)) {
            String[] modulearr = moduleId.split(",");
            for (String s : modulearr) {
                moduleIdList.add(Integer.valueOf(s));
                useredModuleList.add(uiModuleService.selectByKey(s));
            }
            modelAndView.addObject("useredModuleList", useredModuleList);
            modelAndView.addObject("isAdd", "0");
        } else {
            modelAndView.addObject("useredModuleList", "");
            modelAndView.addObject("isAdd", "1");
        }
        modelAndView.addObject("uiModuleList", uiModuleService.getUIModuleBy(uiModule));
        modelAndView.addObject("id", id);
        modelAndView.addObject("name", name);
        modelAndView.addObject("moduleid", moduleId);
        modelAndView.addObject("status", status);
        modelAndView.addObject("projectId", projectId);
        return modelAndView;
    }

    /**
     * 删除case
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/uiTest/deleteCase", method = RequestMethod.POST)
    public ModelAndView deleteModule(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        uiCaseService.delete(id);
        modelAndView.setViewName("uiTest/uiCaseManage");
        return modelAndView;
    }

    /**
     * 编辑case
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/uiTest/editCase", method = RequestMethod.POST)
    public ModelAndView editCase(@RequestBody JSONObject json) {
        ModelAndView modelAndView = new ModelAndView();
        UICase uiCase = new UICase();
        uiCase.setProjectid(json.getInteger("projectId"));
        uiCase.setName(json.getString("name"));
        uiCase.setModuleid(json.getString("moduleId"));
        uiCase.setStatus(json.getString("status"));
        if (StringUtil.isNull(String.valueOf(json.getInteger("id")))) {
            uiCaseService.save(uiCase);
        } else {
            uiCase.setId(json.getInteger("id"));
            uiCaseService.updateAll(uiCase);
        }
        modelAndView.setViewName("redirect:/uiTest/caseManage");
        return modelAndView;
    }

    /**
     * 单个执行
     *
     * @param id
     * @return
     */
    @Synchronized
    @RequestMapping(value = "/uiTest/excuteCase", method = RequestMethod.POST)
    public ModelAndView excuteCase(Integer id) throws IOException, TimeoutException {
        ModelAndView modelAndView = new ModelAndView();
        List<UICase> uiCaseList = new ArrayList<>();
        UICase uiCase = uiCaseService.selectByKey(id);
        uiCaseList.add(uiCase);
        List<Integer> moduleIdList = new ArrayList();
        Object dateMap[][] = new Object[uiCaseList.size()][2];
        for (int i = 0; i < uiCaseList.size(); i++) {
            List<UIStep> uiStepList = new ArrayList<>();
            UIStep uiStep = new UIStep();
            String[] modulearr = uiCaseList.get(i).getModuleid().split(",");
            for (String s : modulearr) {
                moduleIdList.add(Integer.valueOf(s));
            }
            for (int j = 0; j < moduleIdList.size(); j++) {
                uiStep.setModuleid(moduleIdList.get(j));
                uiStepList.addAll(uiStepService.getUIStepBy(uiStep));
            }
            dateMap[i][0] = uiCaseList.get(i);
            dateMap[i][1] = uiStepList;
        }
        setDateMap(dateMap);
        ConsumeMq.setRetryCount(0);
        template.convertAndSend("ExcuteTest", uiCase.getId());
        modelAndView.setViewName("uiTest/uiCaseManage");
        return modelAndView;
    }

    /**
     * 批量执行
     *
     * @param json
     * @return
     */
    @Synchronized
    @RequestMapping(value = "/uiTest/excuteAllCase", method = RequestMethod.POST)
    public ModelAndView excuteCase(@RequestBody JSONArray json) {
        ModelAndView modelAndView = new ModelAndView();
        List<UICase> uiCaseList;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            list.add(Integer.valueOf(json.get(i).toString()));
        }
        uiCaseList = uiCaseService.getUICaseByIdIn(list);
        Object dateMapDetail[][] = new Object[uiCaseList.size()][2];
        for (int i = 0; i < uiCaseList.size(); i++) {
            List<Integer> moduleIdList = new ArrayList();
            List<UIStep> uiStepList = new ArrayList<>();
            UIStep uiStep = new UIStep();
            String[] modulearr = uiCaseList.get(i).getModuleid().split(",");
            for (String s : modulearr) {
                moduleIdList.add(Integer.valueOf(s));
            }
            for (int j = 0; j < moduleIdList.size(); j++) {
                uiStep.setModuleid(moduleIdList.get(j));
                uiStepList.addAll(uiStepService.getUIStepBy(uiStep));
            }
            dateMapDetail[i][0] = uiCaseList.get(i);
            dateMapDetail[i][1] = uiStepList;
        }
        setDateMap(dateMapDetail);
        ConsumeMq.setRetryCount(0);
        template.convertAndSend("ExcuteTest", list.get(0));
        modelAndView.setViewName("uiTest/uiCaseManage");
        return modelAndView;
    }

    public static Object[][] getDateMap() {
        return dateMap;
    }

    public static void setDateMap(Object[][] dateMap) {
        UICaseController.dateMap = dateMap;
    }
}
