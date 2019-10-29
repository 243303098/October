package com.example.service.impl;

import com.example.mapper.ProjectMapper;
import com.example.model.Project;
import com.example.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/24 16:41
 * @Description:
 */
@Service
public class ProjectServiceImpl extends BaseService<Project> implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Project> getAllProject() {
        return projectMapper.selectAll();
    }

    @Override
    public List<Project> getProjectByName(String name) {
        return projectMapper.selectByName(name);
    }
}
