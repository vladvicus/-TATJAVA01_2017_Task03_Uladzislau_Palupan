package com.epam.catalog.service.factory;

import com.epam.catalog.service.BookService;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.impl.BookServiceImpl;
import com.epam.catalog.service.impl.DiskServiceImpl;
import com.epam.catalog.service.impl.FilmServiceImpl;

/**
 * Created by Uladzislau_Palupan on 1/30/2017.
 */
public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final BookService bookService = new BookServiceImpl();
    private final FilmService filmService = new FilmServiceImpl();
    private final DiskService diskService = new DiskServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance()

    {
        return instance;
    }

    public BookService getBookService() {
        return bookService;
    }

    public FilmService getFilmService() {
        return filmService;
    }

    public DiskService getDiskService() {
        return diskService;
    }
}
