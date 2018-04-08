package com.docker.yyf.entity;

import com.docker.yyf.base.DBIndex;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author 18329
 * 项目
 */
public class Project extends BaseEntity{
    /**
     * 项目id
     */
    @DBIndex(unique = true)
    private String projectId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目角色
     */
    private String projectRole;
    /**
     * 描述
     */
    private String describe;


    public Project() {
    }

    public Project(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
