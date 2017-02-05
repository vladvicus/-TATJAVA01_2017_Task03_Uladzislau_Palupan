package com.epam.catalog.controller.command.impl;

import java.util.List;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.Film;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public class SearchFilmBiggerThanRating implements Command {

	@Override
	public List<?> execute(String request) {
	 
		System.out.println(request);
		request = request.replaceAll("\\s{2,}", " ");
		String[] arr = request.split(",");
		if (arr.length==1) return null;
		for (String element : arr) {
			element.trim();
			System.out.println(element);
		}
		Integer rating = Integer.parseInt(arr[1]);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		FilmService filmService = serviceFactory.getFilmService();
		List<Film> filmsFoundByPrice = null;
		try {
			filmsFoundByPrice = filmService.findFilmsGreaterThanRating(rating);
			

		} catch (ServiceException e) {

			// write log
			System.out.println("SearchFilmsGreaterThanRating:Error during searching procedure");
		}

		return filmsFoundByPrice;
	}

}
