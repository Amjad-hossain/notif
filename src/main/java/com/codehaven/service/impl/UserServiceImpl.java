package com.codehaven.service.impl;

import com.codehaven.dao.AdminDao;
import com.codehaven.domain.User;
import com.codehaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by coder on 10/24/15.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private AdminDao adminDao;

    public User findById(int id) {
        return adminDao.findById(User.class, id);
    }

    public List<User> findAll() {
        return adminDao.findAll("SELECT u FROM User u", User.class);
    }

    public void addUser(User user) {
        adminDao.persist(user);
    }
}