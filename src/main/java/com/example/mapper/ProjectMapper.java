package com.example.mapper;

import com.example.model.Project;
import com.example.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper extends MyMapper<Project> {

    List<Project> selectByName(String name);

}