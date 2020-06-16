package com.example.demo.controller;

import com.example.demo.model.Greeting;
import com.example.demo.service.CustomerService;
import com.example.demo.service.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController
{
    private static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerServiceImpl;

    @GetMapping("/customer")
    public ResponseEntity<Object> greeting()
    {
        log.info("inside customer controller");

        Object customerInfo = customerServiceImpl.getAllCustomers();

        if (customerInfo == null)
        {
            return ResponseEntity.badRequest()
                    .body("No Data as of now.");
        }
        else
        {
            return ResponseEntity.ok()
                    .body(customerInfo);
        }
    }
}
