package com.epam.catalog.dao;

import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.exception.DaoException;

import java.util.List;

public interface FilmDao {

	void addFilm(String film) throws DaoException;

	List<Film> findFilmsByName(String name) throws DaoException;

	List<Film> findFilmsGreaterThanRating(Integer rating) throws DaoException;
}
