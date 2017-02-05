package com.epam.catalog.service;

import com.epam.catalog.bean.Film;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;

public interface FilmService {

	void addFilm(String film) throws ServiceException;

	List<Film> findFilmsByName(String name) throws ServiceException;

	List<Film> findFilmsGreaterThanRating(Integer rating) throws ServiceException;

}