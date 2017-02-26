package com.epam.catalog.service;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;


public interface DiskService {

    List<?> addDisk() throws ServiceException;

   

    List<Disk> findDisksLessThanPrice(Double price) throws ServiceException;

    List<Disk> findDisksByName(String name) throws ServiceException;
}
