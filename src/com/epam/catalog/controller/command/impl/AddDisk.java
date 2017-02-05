package com.epam.catalog.controller.command.impl;

import java.util.ArrayList;
import java.util.List;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public class AddDisk implements Command {

	@Override
	public List<?> execute(String request) {
		
		
			final char paramDelimeter = ',';
			System.out.println(request);

			request = request.replaceAll("\\s{2,}", " ");

			// String message = request.substring(request.indexOf(paramDelimeter));
			
			Disk disk = new Disk();
			Disk newDisk = disk.makeDisk();
			List<Disk> addedDisk = new ArrayList<Disk>();
			addedDisk.add(newDisk);
			
			StringBuffer sb = new StringBuffer();
			sb.append(newDisk.getType() + ",");
			sb.append(newDisk.getName() + ",");
			sb.append(newDisk.getYear() + ",");
			sb.append(newDisk.getPrice());

			String message = "disk," + sb.toString();
			
			System.out.println(message+" added to file!!");
			
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DiskService clientService = serviceFactory.getDiskService();
			try {
				clientService.addDisk(message);

			} catch (ServiceException e) {
				System.out.println(e);
				
			}

			return addedDisk;

	}

}
