package com.codecool.stackoverflowtw.controller.dto;

import com.codecool.stackoverflowtw.dao.model.User;

import java.util.Date;

public record UserDTO(int id, String username, String password, Date created) {
    public UserDTO(User user) {
        this(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getDate()
        );
    }
}
