package com.epam.catalog.dao.factory;

import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.FilmDao;
import com.epam.catalog.dao.impl.BookDaoImpl;
import com.epam.catalog.dao.impl.DiskDaoImpl;
import com.epam.catalog.dao.impl.FilmDaoImpl;

import java.nio.file.Paths;


public final class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();
    private final BookDao bookDao = new BookDaoImpl();
    private final FilmDao filmDao = new FilmDaoImpl();
    private final DiskDao diskDao = new DiskDaoImpl();


    private DaoFactory(){}

    public static DaoFactory getInstance()

    {
        return instance;
    }

    public BookDao getBookDao()
    {
        return bookDao;

    }

    public FilmDao getFilmDao() {
        return filmDao;
    }

    public DiskDao getDiskDao() {
        return diskDao;
    }
}
