package com.example.demo.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.model.Book;
import com.example.demo.model.Greeting;
import com.example.demo.service.RabbitMQService;
import com.example.demo.service.XMParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
public class ExampleController
{

    @Autowired
    RabbitMQService rmqService;

    @Autowired
    XMParser xmParser;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/send")
    public String send(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        try
        {
            rmqService.send(name);
        } catch (IOException e)
        {
            e.printStackTrace();

        } catch (TimeoutException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/recv")
    public String recv(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        try
        {
            return rmqService.receive(name);
        } catch (IOException e)
        {
            e.printStackTrace();

        } catch (TimeoutException e)
        {
            e.printStackTrace();
        }
        return "Failed";
    }


    /* XML parser endpoints*/

    @GetMapping("/marsh")
    public Object marsh(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        try
        {
            xmParser.marshal(name);
        } catch (JAXBException e)
        {
            e.printStackTrace();
            return "JAXB Exception";
        } catch (IOException e)
        {
            e.printStackTrace();
            return "File location error";
        }
        return "XML create successfully";
    }

    @GetMapping("/unmarsh")
    public Object unmarsh(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        Book data = new Book();
        try
        {
            data = xmParser.unmarshall(name);
        } catch (JAXBException e)
        {
            e.printStackTrace();
            return "JAXB Exception";
        } catch (IOException e)
        {
            e.printStackTrace();
            return "File not found";
        }
        return data;
    }

}