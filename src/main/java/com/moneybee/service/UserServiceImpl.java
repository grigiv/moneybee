package com.moneybee.service;

import com.moneybee.dao.UserDAO;
import com.moneybee.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<UserEntity> getUsers() {
        return userDAO.getUsers();
    }

//    @Override
//    @Transactional
//    public UserEntity getUser(int id) {
//        return UserDAO.getUser(id);
//    }
}
