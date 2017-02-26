package com.epam.catalog.dao;

import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.exception.DaoException;

import java.util.Set;

public interface FilmDao {

	void addFilm(String film) throws DaoException;

	Set<Film> readFile() throws DaoException;


}
