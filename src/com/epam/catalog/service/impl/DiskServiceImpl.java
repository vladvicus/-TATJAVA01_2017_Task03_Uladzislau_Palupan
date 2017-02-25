package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class DiskServiceImpl implements DiskService {
	static final String RESPONSE = "Error during searching procedure from DiskServiceImpl ";
	@Override
	public List<Disk> addDisk() throws ServiceException {


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

		System.out.println(message + " added to file!!");
		DaoFactory daoFactory = DaoFactory.getInstance();
		DiskDao diskDao = daoFactory.getDiskDao();
		try {
			diskDao.addDisk(message);

		} catch (DaoException e) {

			throw new ServiceException(RESPONSE+ e);

			// write log


		}
		return addedDisk;
	}

	@Override
	public List<Disk> findDisksByName(String name) throws ServiceException {


		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			DiskDao diskDao = daoFactory.getDiskDao();

			List<Disk> disksFind = diskDao.findDisksByName(name);

			return disksFind;
		} catch (DaoException e) {

			throw new ServiceException(RESPONSE+e);

			// write log
		}

	}

	@Override
	public List<Disk> findDisksLessThanPrice(Double price) throws ServiceException {



		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			DiskDao diskDao = daoFactory.getDiskDao();

			List<Disk> disksFind = diskDao.findDisksLessThanPrice(price);

			return disksFind;
		} catch (DaoException e) {

			throw new ServiceException(RESPONSE+e);

		}
	}

}
