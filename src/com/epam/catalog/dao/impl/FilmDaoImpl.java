package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Film;

import com.epam.catalog.dao.FilmDao;
import com.epam.catalog.dao.exception.DaoException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmDaoImpl implements FilmDao {
	private Set<Film> films = new HashSet<>();
	String datafile = Paths.get("data/units.txt").toAbsolutePath().toString();

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

	@Override
	public void addFilm(String film) throws DaoException {
		FileWriter wr = null;
		try {
			wr = new FileWriter(datafile, true);
			wr.append("\n" + film);
			wr.flush();
			wr.close();
		} catch (IOException e) {

			throw new DaoException();
		}

	}

	@Override
	public List<Film> findFilmsByName(String name) throws DaoException {

		System.out.println("Name-->" + name);
		List<Film> filmsFoundByName = new ArrayList<>();

		try {
			readFile(datafile);

		} catch (IOException e) {

			throw new DaoException(e);
		}
		for (Film oneFilm : films) {
			if (oneFilm.getName().toLowerCase().equals(name.toLowerCase())
					|| (oneFilm.getName().toLowerCase().contains(name.toLowerCase()))) {
				filmsFoundByName.add(oneFilm);
			}
		}
		System.out.println("The list of films with name:" + name);

		return filmsFoundByName;
	}

	@Override
	public List<Film> findFilmsGreaterThanRating(Integer rating) throws DaoException {

		System.out.println("Rating-->" + rating);
		List<Film> filmsFoundByPrice = new ArrayList<>();

		try {
			readFile(datafile);

		} catch (IOException e) {

			throw new DaoException("error in findFilmsGraterThanRating method");
		}
		for (Film oneFilm : films) {
			if (oneFilm.getRating() > (rating)) {
				filmsFoundByPrice.add(oneFilm);
			}
		}

		return filmsFoundByPrice;
	}

	public Set<Film> readFile(String fname) throws IOException {

		FileInputStream fis = new FileInputStream(fname);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line;
		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");
			for (int i = 0; i < data.length; i++) {
				data[i] = data[i].trim();

			}
			if (data[0].startsWith("film")) {

				String name = data[1];
				String country = data[2];
				Integer year = Integer.parseInt(data[3]);
				Integer rating = Integer.parseInt(data[4]);
				films.add(new Film(name, country, year, rating));

			} else
				continue;

		}
		br.close();

		System.out.println("Films are suscessfully loaded from file!");
		return films;
	}
}
