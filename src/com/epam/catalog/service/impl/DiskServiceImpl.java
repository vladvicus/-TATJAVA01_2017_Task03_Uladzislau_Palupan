package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.UniDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DiskServiceImpl implements DiskService {
    static final String RESPONSE = "Error during searching procedure from DiskServiceImpl ";
    static final String PARAMETER = "disk";

    @Override
    public List<Disk> addDisk() throws ServiceException {


        Disk disk = new Disk();
        Disk newDisk = disk.makeDisk();
        List<Disk> addedDisk = new ArrayList<Disk>();
        addedDisk.add(newDisk);

        StringBuilder sb = new StringBuilder();
        sb.append(newDisk.getType() + ",");
        sb.append(newDisk.getName() + ",");
        sb.append(newDisk.getYear() + ",");
        sb.append(newDisk.getPrice());

        String message = "disk," + sb.toString();

        System.out.println(message + " added to file!!");
        DaoFactory daoFactory = DaoFactory.getInstance();

        UniDao uniDao=daoFactory.getUniDao();

        try {
            uniDao.addItem(message);

        } catch (DaoException e) {

            throw new ServiceException(RESPONSE + e);

            // write log


        }
        return addedDisk;
    }

    @Override
    public List<Disk> findDisksByName(String name) throws ServiceException {
        List<Disk> disksFoundByName = new ArrayList<>();



            DaoFactory daoFactory = DaoFactory.getInstance();

            UniDao uniDao=daoFactory.getUniDao();
      try{
            Set<Disk> disksFind = uniDao.readFile(PARAMETER);
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

            UniDao uniDao=daoFactory.getUniDao();

            Set<Disk> disksFind = uniDao.readFile(PARAMETER);

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
