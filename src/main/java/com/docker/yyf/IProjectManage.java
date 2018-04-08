package com.docker.yyf;

import com.docker.yyf.entity.Project;

public interface IProjectManage {
    void save(Project project);


    void update(Project project);

    Project selectOne(Project project);

    void addProject(String id, String projectId);

    void removeProject(String id, String projectId);

    void removeProject(String projectId);

    void updateProject(Project project);

    void updateProject(String id, Project project);

    Project selectByProjectId(String projectId);
}
