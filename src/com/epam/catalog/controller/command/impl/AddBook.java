package com.epam.catalog.controller.command.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public class AddBook implements Command {

	@Override
	public List<?> execute(String request) {
		final char paramDelimeter = ',';
		System.out.println(request);

		request = request.replaceAll("\\s{2,}", " ");

		// String message = request.substring(request.indexOf(paramDelimeter));
		
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
		
		System.out.println(message+" added to file!!");
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService clientService = serviceFactory.getBookService();
		try {
			clientService.addBook(message);

		} catch (ServiceException e) {
			System.out.println(e);
			
		}

		return addedBook;

	}

}
