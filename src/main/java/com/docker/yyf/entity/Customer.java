package com.docker.yyf.entity;

import com.docker.yyf.base.DBIndex;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 18329
 */
public class Customer extends BaseEntity {

    private Integer age;
    @DBIndex(unique = true)
    private String firstName;
    private String lastName;
    private Long test;
    private List<Project> projectList;

    public Long getTest() {
        return test;
    }

    public void setTest(Long test) {
        this.test = test;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void addProject(Project project){
        if (projectList==null){
            projectList=new LinkedList<Project>();
        }
        if (projectList.size()>0){
            for (Project p:projectList){
                if (project.getProjectId().equals(p.getProjectId())){
                    throw new RuntimeException("project已经存在");
                }
            }
        }
        this.projectList.add(project);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
