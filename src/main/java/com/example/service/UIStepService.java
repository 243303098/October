package com.example.service;

import com.example.model.UIStep;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/9 11:42
 * @Description:
 */
public interface UIStepService extends IService<UIStep> {

    List<UIStep> getUIStepBy(UIStep uiStep);

    int saveByList(List<UIStep> uiStepList);

    int updateByList(List<UIStep> uiStepList);

    int deleteBymoduleId(Integer moduleId);

}
