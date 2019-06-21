package com.moneybee.service;

import com.moneybee.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getUsers();
//    UserEntity getUser(int id);
}
