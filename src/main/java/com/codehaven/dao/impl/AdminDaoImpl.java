package com.codehaven.dao.impl;

import com.codehaven.dao.AdminDao;
import com.codehaven.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by coder on 1/23/16.
 */
@Repository("adminDao")
public class AdminDaoImpl implements AdminDao{

    private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);

    @PersistenceContext private EntityManager entityManager;

    @Override
    public <T> T findById(Class<T> kClass, Object key) {

        return this.entityManager.find(kClass, key);
    }

    @Override
    public <T> List<T> findAll(String jpaString, Class<T> resultClass) {

        return this.entityManager.createQuery(jpaString, resultClass).getResultList();
    }

    @Override
    public void persist(Object entity) {

        this.entityManager.persist(entity);
    }
}
