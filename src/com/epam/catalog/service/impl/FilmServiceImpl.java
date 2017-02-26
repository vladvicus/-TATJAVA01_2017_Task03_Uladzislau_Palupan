package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.UniDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FilmServiceImpl implements FilmService {
    static final String RESPONSE = "Error during searching procedure from FilmServiceImpl ";
    static final String PARAMETER = "film";

    @Override
    public List<Film> addFilm() throws ServiceException {


        Film film = new Film();
        Film newFilm = film.makeFilm();
        List<Film> addedFilm = new ArrayList<>();
        addedFilm.add(newFilm);

        StringBuilder sb = new StringBuilder();
        sb.append(newFilm.getName() + ",");
        sb.append(newFilm.getCountry() + ",");
        sb.append(newFilm.getYear() + ",");
        sb.append(newFilm.getRating());

        String message = "film," + sb.toString();
        DaoFactory daoFactory = DaoFactory.getInstance();

        UniDao uniDao = daoFactory.getUniDao();
        try {
            uniDao.addItem(message);
        } catch (DaoException e) {

            throw new ServiceException(RESPONSE + e);

        }
        return addedFilm;
    }

    @Override
    public List<Film> findFilmsByName(String name) throws ServiceException {
        List<Film> filmsFoundByName = new ArrayList<>();

        DaoFactory daoFactory = DaoFactory.getInstance();

        UniDao uniDao = daoFactory.getUniDao();
        try {
            Set<Film> filmsFind = uniDao.readFile(PARAMETER);

            for (Film oneFilm : filmsFind) {
                if (oneFilm.getName().toLowerCase().equals(name.toLowerCase())
                        || (oneFilm.getName().toLowerCase().contains(name.toLowerCase()))) {
                    filmsFoundByName.add(oneFilm);
                }
            }
            System.out.println("The list of films with name:" + name);


        } catch (DaoException e) {

            throw new ServiceException(RESPONSE + e);

            // write log
        }
        return filmsFoundByName;
    }

    @Override
    public List<Film> findFilmsGreaterThanRating(Integer rating) throws ServiceException {
        List<Film> filmsFoundByRating = new ArrayList<>();

        DaoFactory daoFactory = DaoFactory.getInstance();

        UniDao uniDao = daoFactory.getUniDao();
        try {
            Set<Film> filmsFind = uniDao.readFile(PARAMETER);

            for (Film oneFilm : filmsFind) {
                if (oneFilm.getRating() > (rating)) {
                    filmsFoundByRating.add(oneFilm);
                }
            }


        } catch (DaoException e) {

            throw new ServiceException(RESPONSE + e);

            // write log
        }
        return filmsFoundByRating;
    }


}
