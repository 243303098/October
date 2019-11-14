package com.example.service;

import com.example.model.UIKeyword;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/30 10:33
 * @Description:
 */

public interface UIKeywordService extends IService<UIKeyword>{

        List<UIKeyword> getAllUIKeyword();

        List<UIKeyword> getUIKeywordById(String id);

}
