package com.docker.yyf;

import com.docker.yyf.base.BasePage;
import com.docker.yyf.entity.BaseQuery;
import com.docker.yyf.entity.Customer;
import com.docker.yyf.entity.Project;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author 18329
 */
public interface ICustomerManager {

    void save(Customer customer);

    void update(Customer customer);

    Customer selectById(String id);

    Customer selectOne(Customer Customer);

    List<Customer>  select(Customer customer);


    BasePage<Customer> page(BaseQuery<Customer> query);

    void addProject(String id, Project project);

    void addProject(List<String> ids, Project project);

    void exprot(HttpServletResponse response) throws Exception;

    @Async
    String test();
}
