package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService
{
    private static Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Object getAllCustomers()
    {
        log.info("inside service class");
        Iterable<Customer> data = customerRepository.findAll();

        return data;
    }
}
