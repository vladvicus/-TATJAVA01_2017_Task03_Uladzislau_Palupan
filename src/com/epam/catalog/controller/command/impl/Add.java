package com.epam.catalog.controller.command.impl;


import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;

public class Add implements Command {

    @Override
    public List<?> execute(String request) {
        if (request.contains("book")){
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BookService bookService = serviceFactory.getBookService();
            try {
               return bookService.addBook();

            } catch (ServiceException e) {
                System.out.println(e);

            }
        } else if (request.contains("disk")){
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DiskService diskService = serviceFactory.getDiskService();
            try {
               return  diskService.addDisk();

            } catch (ServiceException e) {
                System.out.println(e);

            }
        } else if (request.contains("film")){
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            FilmService filmService = serviceFactory.getFilmService();
            try {
               return filmService.addFilm();

            } catch (ServiceException e) {
                System.out.println(e);

            }
        }






        return null;
    }
}
