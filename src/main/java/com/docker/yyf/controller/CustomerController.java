package com.docker.yyf.controller;


import com.docker.yyf.ICustomerManager;
import com.docker.yyf.base.BasePage;
import com.docker.yyf.entity.BaseQuery;
import com.docker.yyf.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 18329
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private ICustomerManager customerManage;

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public BasePage<Customer> saveCustomer(BaseQuery<Customer> query){
        return customerManage.page(query);
    }
}
