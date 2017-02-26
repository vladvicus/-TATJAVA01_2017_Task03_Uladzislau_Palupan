package com.epam.catalog.dao.factory;

import com.epam.catalog.dao.UniDao;
import com.epam.catalog.dao.impl.UniDaoImpl;


public final class DaoFactory {

    private static final DaoFactory instance = new DaoFactory();

    private final UniDao uniDao = new UniDaoImpl();

    private DaoFactory(){}

    public static DaoFactory getInstance()

    {
        return instance;
    }

public UniDao getUniDao(){
    return uniDao;
}


}
