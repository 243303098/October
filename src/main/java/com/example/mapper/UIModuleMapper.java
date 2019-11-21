package com.example.mapper;

import com.example.model.UIModule;
import com.example.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UIModuleMapper extends MyMapper<UIModule> {

    List<UIModule> selectBy(UIModule uiModule);

    List<UIModule> selectByIdIn(List<Integer> list);

}