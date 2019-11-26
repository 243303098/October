package com.example.mapper;

import com.example.model.UICase;
import com.example.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UICaseMapper extends MyMapper<UICase> {

    List<UICase> selectBy(UICase uiCase);

    List<UICase> selectByIdIn(List<Integer> list);

}