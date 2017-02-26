package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BookServiceImpl implements BookService {
    static final String RESPONSE = "Error during searching procedure from BookServiceImpl ";

    @Override
    public List<Book> addBook() throws ServiceException {


        Book book = new Book();
        Book newBook = book.makeBook();
        List<Book> addedBook = new ArrayList<Book>();
        addedBook.add(newBook);

        StringBuffer sb = new StringBuffer();
        sb.append(newBook.getAuthor() + ",");
        sb.append(newBook.getName() + ",");
        sb.append(newBook.getPages() + ",");
        sb.append(newBook.getPrice());
        String message = "book," + sb.toString();

        DaoFactory daoFactory = DaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        try {
            bookDao.addBook(message);


        } catch (DaoException e) {

            throw new ServiceException(RESPONSE + e);

            // write log
        }
        return addedBook;
    }

    @Override
    public List<Book> findBooksLessThenPrice(Double price) throws ServiceException {
        List<Book> booksFoundByPrice = new ArrayList<>();
        if (price < 0) {
            System.out.println("Price cannot be negative.Reenter the price");
            return Collections.emptyList();
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookDao bookDao = daoFactory.getBookDao();
        try {
            Set<Book> allBooks = bookDao.readFile();
            for (Book oneBook : allBooks) {
                if (oneBook.getPrice() < (price)) {
                    booksFoundByPrice.add(oneBook);
                }
            }

        } catch (DaoException e) {
            throw new ServiceException(RESPONSE + e);
        }
        if (booksFoundByPrice.isEmpty()) {
            return Collections.emptyList();
        } else {
            return booksFoundByPrice;
        }
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws ServiceException {
        List<Book> booksFoundByAuthorName = new ArrayList<>();
        if (author.isEmpty()) {
            System.out.println("Searching on empty string!!Reenter the parameter author.");
            return Collections.emptyList();
        }

            DaoFactory daoFactory = DaoFactory.getInstance();
            BookDao bookDao = daoFactory.getBookDao();
        try {
            Set<Book> allBbooks = bookDao.readFile();
            for (Book oneBook : allBbooks) {

                if (oneBook.getAuthor().toLowerCase().equals(author.toLowerCase())
                        || (oneBook.getAuthor().toLowerCase().contains(author.toLowerCase()))) {
                    booksFoundByAuthorName.add(oneBook);
                }
            }


        } catch (DaoException e) {

            throw new ServiceException(RESPONSE + e);
            // write log
        }
        if (booksFoundByAuthorName.isEmpty()) {
            return Collections.emptyList();
        } else {
            return booksFoundByAuthorName;
        }
    }
}
