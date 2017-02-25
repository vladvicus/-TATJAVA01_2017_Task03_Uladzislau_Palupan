package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

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


		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			BookDao bookDao = daoFactory.getBookDao();

			List<Book> booksFind = bookDao.findBooksLessThenPrice(price);

			return booksFind;
		} catch (DaoException e) {

			throw new ServiceException(RESPONSE+e);

		}
	}

	@Override
	public List<Book> findBooksByAuthor(String author) throws ServiceException {

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			BookDao bookDao = daoFactory.getBookDao();

			List<Book> booksFind = bookDao.findBooksByAuthor(author);

			return booksFind;
		} catch (DaoException e) {

			throw new ServiceException(RESPONSE+e);

			// write log
		}

	}
}
