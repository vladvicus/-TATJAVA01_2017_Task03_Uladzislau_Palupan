package com.epam.catalog.controller.command.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.catalog.bean.Film;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public class AddFilm implements Command {

	@Override
	public List<?> execute(String request) {
		
		System.out.println(request);

		request = request.replaceAll("\\s{2,}", " ");

		// String message = request.substring(request.indexOf(paramDelimeter));
		
		Film film = new Film();
		Film newFilm = film.makeFilm();
		List<Film> addedFilm = new ArrayList<Film>();
		addedFilm.add(newFilm);
		
		StringBuffer sb = new StringBuffer();
		sb.append(newFilm.getName() + ",");
		sb.append(newFilm.getCountry() + ",");
		sb.append(newFilm.getYear() + ",");
		sb.append(newFilm.getRating());

		String message = "film," + sb.toString();
		
		System.out.println(message+" added to file!!");
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FilmService clientService = serviceFactory.getFilmService();
		try {
			clientService.addFilm(message);

		} catch (ServiceException e) {
			System.out.println(e);
			
		}

		return addedFilm;

	}

}
