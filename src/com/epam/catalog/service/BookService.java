package com.epam.catalog.service;

import com.epam.catalog.bean.Book;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;


public interface BookService {


    void addBook(String book) throws ServiceException;



   // Book findOneBook(String name) throws ServiceException;

    List<Book> findBooksLessThenPrice(Double price) throws ServiceException;

    List<Book> findBooksByAuthor(String author) throws ServiceException;
}