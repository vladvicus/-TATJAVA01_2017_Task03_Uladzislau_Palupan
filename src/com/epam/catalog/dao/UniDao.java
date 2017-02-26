package com.epam.catalog.dao;


import com.epam.catalog.dao.exception.DaoException;

import java.util.Set;

public interface UniDao {

    void addItem(String item) throws DaoException;

  <T> Set<T> readFile(String item) throws DaoException;

}
