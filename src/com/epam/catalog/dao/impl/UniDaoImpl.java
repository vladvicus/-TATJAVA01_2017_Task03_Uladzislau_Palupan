package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.UniDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.view.Main;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class UniDaoImpl implements UniDao {


    @Override
    public void addItem(String item) throws DaoException {
        FileWriter wr = null;
        try {
            wr = new FileWriter(Main.DATAFILE, true);
            wr.append("\n" + item);
            wr.flush();
            wr.close();
        } catch (IOException e) {

            throw new DaoException(e);
        }
    }

    @Override
    public <T> Set<T> readFile(String item) throws DaoException {
        Set<T> items = new HashSet<T>();
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
                if (data[0].startsWith("book") && (data[0].equals(item))) {

                    String name = data[1];
                    String author = data[2];
                    Integer page = Integer.parseInt(data[3]);
                    Double price = Double.parseDouble(data[4]);
                    items.add((T) new Book(name, author, page, price));

                } else  if (data[0].startsWith("disk")&&(data[0].equals(item))) {

                    String type = data[1];
                    String name = data[2];
                    Integer year = Integer.parseInt(data[3]);
                    Double price = Double.parseDouble(data[4]);
                    items.add((T) new Disk(type, name, year, price));

                } else if (data[0].startsWith("film")&&(data[0].equals(item))) {

                    String name = data[1];
                    String country = data[2];
                    Integer year = Integer.parseInt(data[3]);
                    Integer rating = Integer.parseInt(data[4]);
                    items.add((T) new Film(name, country, year, rating));

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
        System.out.println(item+"s are suscessfully loaded from file!!!");
        return items;
    }


}

