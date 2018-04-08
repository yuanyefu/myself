package com.docker.yyf.Repository;

import com.docker.yyf.base.MongodbBaseDao;
import com.docker.yyf.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectDaoImpl extends MongodbBaseDao<Project> implements IProjectDao {
    @Override
    protected Class<Project> getEntityClass() {
        return Project.class;
    }
}
