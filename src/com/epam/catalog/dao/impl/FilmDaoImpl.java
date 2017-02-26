package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.FilmDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.view.Main;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FilmDaoImpl implements FilmDao {
    private Set<Film> films = new HashSet<>();


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
            wr = new FileWriter(Main.DATAFILE, true);
            wr.append("\n" + film);
            wr.flush();
            wr.close();
        } catch (IOException e) {

            throw new DaoException();
        }

    }


    @Override
    public Set<Film> readFile() throws DaoException {

        BufferedReader br = null;
        try {
            FileInputStream fis = new FileInputStream(Main.DATAFILE);
            br = new BufferedReader(new InputStreamReader(fis));
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
        } catch (IOException e) {
            throw new DaoException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new DaoException(e);
            }
        }


        System.out.println("Films are suscessfully loaded from file!");
        return films;
    }
}
