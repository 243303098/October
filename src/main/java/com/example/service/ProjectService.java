package com.example.service;

import com.example.model.Project;
import java.util.List;

/**
 * @Auther: Leo.hu
 * @Date: 2019/10/24 16:40
 * @Description:
 */
public interface ProjectService extends IService<Project>{

    List<Project> getAllProject();

    List<Project> getProjectBy(Project project);

    List<Project> getProjectByName(String name);

}
