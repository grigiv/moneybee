package com.moneybee.dao;

import com.moneybee.entity.UserEntity;

import java.util.List;

public interface UserDAO {
    List<UserEntity> getUsers();
    UserEntity getUser(int id);
}
