package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.dao.model.User;

import java.util.List;

public interface UsersDAO {
    List<User> getAllUser();
    User getUserById(int id);
    int addUser(NewUserDTO user);
}
