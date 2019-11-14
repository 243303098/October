package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.model.*;
import com.example.service.*;
import com.example.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/9 11:42
 * @Description:
 */

@RestController
public class UIStepController {

    @Autowired
    private UIStepService uiStepService;

    @Autowired
    private UIElementService uiElementService;

    @Autowired
    private UIKeywordService uiKeywordService;

    @Autowired
    private UIModuleService moduleService;

    /**
     * editUIStepPage
     * @param moduleId
     * @return
     */
    @RequestMapping(value = "/uiTest/editUIStep", method = RequestMethod.GET)
    public ModelAndView editUIStepPage(@RequestParam(value = "moduleId") Integer moduleId){
        ModelAndView modelAndView = new ModelAndView();
        UIStep uiStep = new UIStep();
        uiStep.setModuleid(moduleId);
        modelAndView.addObject("stepList", uiStepService.getUIStepBy(uiStep));
        UIModule uiModule = new UIModule();
        uiModule.setId(moduleId);
        List<UIModule> uiModuleList = moduleService.getUIModuleBy(uiModule);
        UIElement uiElement = new UIElement();
        uiElement.setProjectid(uiModuleList.get(0).getProjectid());
        modelAndView.addObject("moduleId",moduleId);
        modelAndView.addObject("uiElementList", uiElementService.getUIElementBy(uiElement));
        modelAndView.addObject("uiKeywordList", uiKeywordService.getAllUIKeyword());
        modelAndView.setViewName("uiTest/uiModuleStepEdit");
        return modelAndView;
    }

    /**
     * 新增或保存uiStep
     * @param result
     * @return
     */
    @RequestMapping(value = "/uiTest/saveUIStep", method = RequestMethod.POST)
    public ModelAndView saveUIStep(@RequestBody JSONArray result) {
        JSONArray jsonArray = new JSONArray();
        ModelAndView modelAndView = new ModelAndView();
        //获取moduleId查询对应的模块参数
        Integer moduleId = result.getJSONObject(0).getInteger("moduleId");
        UIModule uiModule = new UIModule();
        uiModule.setId(moduleId);
        List<UIModule> uiModuleList = moduleService.getUIModuleBy(uiModule);
        JSONObject paramJson = JSONObject.parseObject(uiModuleList.get(0).getParamdata());

        for (int i = 0; i < result.size(); i++) {
            JSONObject uiStepDetail = result.getJSONObject(i);
            //替换参数
//            if (!StringUtil.isNull(uiStepDetail.getString("datakey"))){
//                uiStepDetail.put("datakey", paramJson.getString(uiStepDetail.getString("datakey")));
//            }
            //替换uiElementName为id
            UIElement uiElement = new UIElement();
            uiElement.setName(uiStepDetail.getString("uiElementName"));
            uiElement.setProjectid(uiModuleList.get(0).getProjectid());
            uiStepDetail.put("uiElementName", uiElementService.getUIElementBy(uiElement).get(0).getId());
            jsonArray.add(uiStepDetail);
        }

        List<UIStep> uiStepList_insert = new ArrayList<>();
        List<UIStep> uiStepList_update = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            UIStep uiStep = new UIStep();
            uiStep.setModuleid(moduleId);
            uiStep.setActionid(jsonArray.getJSONObject(i).getString("actionid"));
            uiStep.setComment(jsonArray.getJSONObject(i).getString("comment"));
            uiStep.setDatakey(jsonArray.getJSONObject(i).getString("datakey"));
            uiStep.setElementid(Integer.valueOf(jsonArray.getJSONObject(i).getString("uiElementName")));
            uiStep.setName(jsonArray.getJSONObject(i).getString("stepName"));
            uiStep.setSort(Integer.valueOf(jsonArray.getJSONObject(i).getString("sort")));
            if (StringUtil.isNull(String.valueOf(jsonArray.getJSONObject(i).getInteger("id")))){
                uiStepList_insert.add(uiStep);
            }else {
                uiStep.setId(jsonArray.getJSONObject(i).getInteger("id"));
                uiStepList_update.add(uiStep);
            }
        }
        if (uiStepList_update.size() > 0){
            uiStepService.updateByList(uiStepList_update);
        }
        if (uiStepList_insert.size() > 0){
            uiStepService.saveByList(uiStepList_insert);
        }
        modelAndView.setViewName("uiTest/uiModuleStepEdit");
        return modelAndView;
    }

    /**
     * 根据id删除step
     * @param id
     * @return
     */
    @RequestMapping(value = "/uiTest/deleteStep", method = RequestMethod.POST)
    public ModelAndView deteteStep(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        uiStepService.delete(id);
        modelAndView.setViewName("uiTest/uiModuleStepEdit");
        return modelAndView;
    }

}
