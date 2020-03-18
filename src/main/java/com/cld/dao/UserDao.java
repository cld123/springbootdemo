package com.cld.dao;

import com.cld.entity.User;

import java.util.List;


public interface UserDao {
    List<User> findAll();
}
