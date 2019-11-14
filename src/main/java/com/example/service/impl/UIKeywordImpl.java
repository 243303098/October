package com.example.service.impl;

import com.example.mapper.UIKeywordMapper;
import com.example.model.UIKeyword;
import com.example.service.UIKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/30 10:35
 * @Description:
 */
@Service
public class UIKeywordImpl extends BaseService<UIKeyword> implements UIKeywordService {

    @Autowired
    private UIKeywordMapper uiKeywordMapper;

    @Override
    public List<UIKeyword> getAllUIKeyword() {
        return uiKeywordMapper.selectAll();
    }

    @Override
    public List<UIKeyword> getUIKeywordById(String id) {
        return uiKeywordMapper.selectById(id);
    }
}
