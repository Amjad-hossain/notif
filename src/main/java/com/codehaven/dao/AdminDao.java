package com.codehaven.dao;


import com.codehaven.domain.Organization;

import java.util.List;

/**
 * Created by coder on 1/23/16.
 */
public interface AdminDao{

    <T> T findById( Class<T> kClass, Object key);

    <T> List<T> findAll(String jpaString, Class<T> resultClass);

    void persist(Object entity);
}
