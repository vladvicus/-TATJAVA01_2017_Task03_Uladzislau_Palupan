package com.epam.catalog.dao;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.exception.DaoException;

import java.util.List;

/*
 */
public interface BookDao {
    void addBook(String book) throws DaoException;
    List<Book> findBooksLessThenPrice(Double price) throws DaoException;
    List<Book> findBooksByAuthor(String author) throws DaoException;
    
}
