package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;


@Component
public class XMParser
{
    public final static String FILE_PATH = "./src/main/resources/templates/";
    public final static String FILE_EXTENSION = ".xsd";

    public void marshal(String xmlName) throws JAXBException, IOException
    {
        Book book = new Book();
        book.setId(1L);
        book.setName(xmlName);
        book.setAuthor("Author1");
        book.setDate(new Date());
        System.out.println("added log");
        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(book, new File(FILE_PATH + xmlName + FILE_EXTENSION));
    }

    public Book unmarshall(String xmlName) throws JAXBException, IOException
    {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        return (Book) context.createUnmarshaller()
                .unmarshal(new FileReader(FILE_PATH + xmlName + FILE_EXTENSION));
    }
}
