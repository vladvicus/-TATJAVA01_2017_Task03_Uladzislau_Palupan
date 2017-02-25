package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;
import java.util.List;

public class BookServiceImpl implements BookService {
	static final String RESPONSE = "Error during searching procedure from BookServiceImpl ";
	@Override
	public void addBook(String book) throws ServiceException {
		String response = null;
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			BookDao bookDao = daoFactory.getBookDao();

			bookDao.addBook(book);

		} catch (DaoException e) {

			throw new ServiceException(RESPONSE+e);

			// write log
		}
	}

	@Override
	public List<Book> findBooksLessThenPrice(Double price) throws ServiceException {
		String response = null;

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

		String response = null;
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
