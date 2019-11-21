package com.example.service;

import com.example.model.UIModule;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/30 17:37
 * @Description:
 */
public interface UIModuleService extends IService<UIModule>{

    List<UIModule> getUIModuleBy(UIModule uiModule);

    List<UIModule> getUIModuleByIdIn(List<Integer> idList);

}
