package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;

public class DiskServiceImpl implements DiskService {

	@Override
	public void addDisk(String disk) throws ServiceException {
		String response = null;
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			DiskDao diskDao = daoFactory.getDiskDao();

			diskDao.addDisk(disk);

		} catch (DaoException e) {
			response = "Error during searching procedure from DiskServiceImpl";
			throw new ServiceException(response);

			// write log
		}
	}

	@Override
	public List<Disk> findDisksByName(String name) throws ServiceException {

		String response = null;
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			DiskDao diskDao = daoFactory.getDiskDao();

			List<Disk> disksFind = diskDao.findDisksByName(name);

			return disksFind;
		} catch (DaoException e) {
			response = "Error during searching procedure from DiskServiceImpl";
			throw new ServiceException(response);

			// write log
		}

	}

	@Override
	public List<Disk> findDisksLessThanPrice(Double price) throws ServiceException {

		String response = null;

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			DiskDao diskDao = daoFactory.getDiskDao();

			List<Disk> disksFind = diskDao.findDisksLessThanPrice(price);

			return disksFind;
		} catch (DaoException e) {
			response = "Error during searching procedure from DiskServiceImpl";
			throw new ServiceException(response);

		}
	}

}
