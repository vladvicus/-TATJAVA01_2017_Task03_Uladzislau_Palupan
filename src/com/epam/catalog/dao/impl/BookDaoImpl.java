package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.view.Main;

import java.io.*;
import java.util.*;

public class BookDaoImpl implements BookDao {

    private Set<Book> books = new HashSet<>();


    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public void addBook(String book) throws DaoException {
        FileWriter wr = null;
        try {
            wr = new FileWriter(Main.DATAFILE, true);
            wr.append("\n" + book);
            wr.flush();
            wr.close();
        } catch (IOException e) {

            throw new DaoException(e);
        }

    }


    @Override
    public Set<Book> readFile() throws DaoException {

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
                if (data[0].startsWith("book")) {

                    String name = data[1];
                    String author = data[2];
                    Integer page = Integer.parseInt(data[3]);
                    Double price = Double.parseDouble(data[4]);
                    books.add(new Book(name, author, page, price));

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
        System.out.println("Books are suscessfully loaded from file!");
        return books;
    }


}
