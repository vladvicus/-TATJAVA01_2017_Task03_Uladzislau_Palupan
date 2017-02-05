package com.epam.catalog.controller.command.impl;

import java.util.List;

import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public class SearchBookLessThanPrice implements Command {

	@Override
	public List<?> execute(String request) {
		
        String[] arr = request.split(",");
        if (arr.length==1) return null;
        for (String element : arr) {
            element.trim();
            System.out.println(element);
        }
        Double price =Double.parseDouble(arr[1]);

       
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService clientService = serviceFactory.getBookService();
        List<Book> booksFoundByPrice=null;
        try {
            booksFoundByPrice=clientService.findBooksLessThenPrice(price);
           /* for(Book oneBook:booksFoundByPrice){
                System.out.println(oneBook.toString());
            }*/
           

        } catch (ServiceException e) {

            // write log
            System.out.println("Controller,SearchBookByPrice:Error during searching procedure");
        }

        return booksFoundByPrice;

    
	}

}
