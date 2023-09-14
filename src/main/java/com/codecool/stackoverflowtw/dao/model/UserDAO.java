package com.codecool.stackoverflowtw.dao.model;

import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;

import java.util.List;

public interface UserDAO {
    List<User> getAllUser();
    User getUserById(int id);
    int addUser(NewUserDTO user);
}
