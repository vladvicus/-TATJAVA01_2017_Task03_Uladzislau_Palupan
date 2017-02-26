package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.view.Main;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class DiskDaoImpl implements DiskDao {
    private Set<Disk> disks = new HashSet<>();


    public Set<Disk> getDisks() {
        return disks;
    }

    public void setDisks(Set<Disk> disks) {
        this.disks = disks;
    }

    @Override
    public void addDisk(String disk) throws DaoException {
        FileWriter wr = null;
        try {
            wr = new FileWriter(Main.DATAFILE, true);
            wr.append("\n" + disk);
            wr.flush();
            wr.close();
        } catch (IOException e) {

            throw new DaoException();
        }

    }


    @Override
    public Set<Disk> readFile() throws DaoException {
        BufferedReader br = null;
        try {
            FileInputStream fis = new FileInputStream(Main.DATAFILE);
            br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();

                }
                if (data[0].startsWith("disk")) {

                    String type = data[1];
                    String name = data[2];
                    Integer year = Integer.parseInt(data[3]);
                    Double price = Double.parseDouble(data[4]);
                    disks.add(new Disk(type, name, year, price));

                } else
                    continue;

            }

        } catch (IOException e) {
            throw new DaoException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {

                System.out.println("Error trying to close InputStream! ");
                throw new DaoException(e);
            }
        }

        System.out.println("Disks are suscessfully loaded from file!");
        return disks;
    }

}
