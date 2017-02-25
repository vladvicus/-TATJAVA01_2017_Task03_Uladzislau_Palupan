package com.epam.catalog.service;

import com.epam.catalog.bean.Film;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;

public interface FilmService {

	List<Film> addFilm() throws ServiceException;

	List<Film> findFilmsByName(String name) throws ServiceException;

	List<Film> findFilmsGreaterThanRating(Integer rating) throws ServiceException;

}