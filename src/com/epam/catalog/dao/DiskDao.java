package com.epam.catalog.dao;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.exception.DaoException;

import java.util.Set;

public interface DiskDao {

	void addDisk(String disk) throws DaoException;
	Set<Disk> readFile() throws DaoException;

}
