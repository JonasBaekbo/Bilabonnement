package com.example.bilabonnement.models;

public interface IModel <T> {

    public T getValueFromID(int id);

    public int getIDFromValue(T entity);
}
