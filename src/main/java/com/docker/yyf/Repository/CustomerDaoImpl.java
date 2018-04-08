package com.docker.yyf.Repository;

import com.docker.yyf.base.MongodbBaseDao;
import com.docker.yyf.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerDaoImpl extends MongodbBaseDao<Customer> implements ICustomerDao {
    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }
}
