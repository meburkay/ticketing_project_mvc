package com.cydeo.service;

import java.util.List;

public interface CrudService<T,ID> {
//We create the common crudeservice operations here as a parent interface. The service interfaces will extend this interface and if they have unique operations we will add them directly at their interfaces.
T save(T obj);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    void update(T obj);

}

