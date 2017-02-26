package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;

public class SearchDiskLessThanPrice implements Command {

	@Override
	public List<?> execute(String request) {

		request = request.replaceAll("\\s{2,}", " ");
		String[] arr = request.split(",");
		if (arr.length==1) return null;
		for (String element : arr) {
			element.trim();
			System.out.println(element);
		}
		Double price = Double.parseDouble(arr[1]);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		DiskService diskService = serviceFactory.getDiskService();
		List<Disk> disksFoundByPrice = null;
		try {
			disksFoundByPrice = diskService.findDisksLessThanPrice(price);
			

		} catch (ServiceException e) {

			// write log
			System.out.println("SearchDiskByPrice:Error during searching procedure");
		}

		return disksFoundByPrice;

	}

}
