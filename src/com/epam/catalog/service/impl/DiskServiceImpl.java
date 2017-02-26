package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

            throw new ServiceException(RESPONSE + e);

            // write log


        }
        return addedDisk;
    }

    @Override
    public List<Disk> findDisksByName(String name) throws ServiceException {
        List<Disk> disksFoundByName = new ArrayList<>();


        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DiskDao diskDao = daoFactory.getDiskDao();

            Set<Disk> disksFind = diskDao.readFile();
            for (Disk oneDisk : disksFind) {
                if (oneDisk.getName().toLowerCase().equals(name.toLowerCase()) ||
                        (oneDisk.getName().toLowerCase().contains(name.toLowerCase()))) {
                    disksFoundByName.add(oneDisk);
                }
            }
            System.out.println("The list of disks with name:" + name);



        } catch (DaoException e) {

            throw new ServiceException(RESPONSE + e);

            // write log
        }
        return disksFoundByName;
    }

    @Override
    public List<Disk> findDisksLessThanPrice(Double price) throws ServiceException {

        List<Disk> disksFoundByPrice = new ArrayList<>();

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DiskDao diskDao = daoFactory.getDiskDao();

            Set<Disk> disksFind = diskDao.readFile();

            for (Disk oneDisk : disksFind) {
                if (oneDisk.getPrice() < (price)) {
                    disksFoundByPrice.add(oneDisk);
                }
            }



        } catch (DaoException e) {

            throw new ServiceException(RESPONSE + e);

        }
        return disksFoundByPrice;
    }

}
