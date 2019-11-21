package com.example.service;

import com.example.model.UICase;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/11/15 13:50
 * @Description:
 */
public interface UICaseService extends IService<UICase>{

    List<UICase> getUICaseBy(UICase uiCase);

}
