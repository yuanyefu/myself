package com.docker.yyf.manage;

import com.docker.yyf.ICustomerManager;
import com.docker.yyf.IProjectManage;
import com.docker.yyf.Repository.IProjectDao;
import com.docker.yyf.entity.Customer;
import com.docker.yyf.entity.Project;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 18329
 * 项目管理
 */
@Service
public class ProjectManageImpl implements IProjectManage {
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private IProjectDao dao;
    @Override
    public void save(Project project){
        dao.save(project);
    }
    @Override
    public void update(Project project){
        dao.updateFirst(project);
        this.updateProject(project);
    }
    @Override
    public Project selectOne(Project project){
       return dao.selectOne(project);
    }
    @Override
    public void addProject(String id, String projectId){
        Customer customer = customerManager.selectById(id);
        if ( customer == null ){
            throw new RuntimeException("customer不能为空");
        }
        Project select = new Project();
        select.setProjectId(projectId);
        Project project = dao.selectOne(select);
        if (project == null){
            throw new RuntimeException("project不能为空");
        }
        customerManager.addProject(customer.getId(),project);
    }

    @Override
    public void removeProject(String id, String projectId){
        Customer customer = customerManager.selectById(id);
        if (customer==null){
            return;
        }
        List<Project> projectList = customer.getProjectList();
        if (CollectionUtils.isEmpty(projectList)){
            return;
        }
        for (Project list:projectList){
            if (list.getProjectId().equals(projectId)) {
                projectList.remove(list);
                break;
            }
        }
        Customer update = new Customer();
        update.setId(customer.getId());
        update.setProjectList(projectList);
        customerManager.update(update);
    }
    @Override
    public void removeProject(String projectId){
        Customer select = new Customer();
        Project project = new Project(projectId);
        select.addProject(project);
        List<Customer> list = customerManager.select(select);
        for (Customer customer:list){
            this.removeProject(customer.getId(),projectId);
        }
    }

    @Override
    public void updateProject(Project project){
        Customer select = new Customer();
        select.addProject(new Project(project.getProjectId()));
        List<Customer> list = customerManager.select(select);
        for (Customer customer:list){
            this.updateProject(customer.getId(),project);
        }
    }

    @Override
    public void updateProject(String id, Project project){
        Customer customer = customerManager.selectById(id);
        if (customer==null){
            return;
        }
        List<Project> projectList = customer.getProjectList();
        if (CollectionUtils.isEmpty(projectList)){
            return;
        }
        for (Project pro:projectList){
           if (pro.getProjectId().equals(project.getProjectId())){
               projectList.remove(pro);
               projectList.add(project);
               break;
           }
        }
        Customer update = new Customer();
        update.setId(customer.getId());
        update.setProjectList(projectList);
        customerManager.update(update);
    }
    @Override
    public Project selectByProjectId(String projectId){
        if (StringUtils.isBlank(projectId)){
            return null;
        }
        return dao.selectOne(new Project(projectId));
    }
}
