package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Film;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;

public class SearchFilmByName implements Command {

    @Override
    public List<?> execute(String request) {

        request = request.replaceAll("\\s{2,}", " ");
        String[] arr = request.split(",");
        if (arr.length == 1) return null;
        for (String element : arr) {
            element.trim();
            System.out.println(element);
        }
        String name = arr[1];

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();
        List<Film> filmsFoundByName = null;
        try {
            filmsFoundByName = filmService.findFilmsByName(name);

        } catch (ServiceException e) {

            // write log
            System.out.println("Controller,SearchFilmByName:Error during searching procedure");
        }

        return filmsFoundByName;
    }

}
