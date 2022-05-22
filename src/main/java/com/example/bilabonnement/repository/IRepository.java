package com.example.bilabonnement.repository;

import java.util.ArrayList;
import java.util.List;

public interface IRepository<T> {

    //Create
    public boolean create(T entity);

    //Read
    public T getSingleById(int id);

    public ArrayList<T> getAllEntities();

    //Update
    public boolean update(T entity);

    //Delete
}


