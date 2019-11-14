package com.example.mapper;

import com.example.model.UIKeyword;
import com.example.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UIKeywordMapper extends MyMapper<UIKeyword> {

    List<UIKeyword> selectById(String id);

}