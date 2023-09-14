package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.UserDTO;
import com.codecool.stackoverflowtw.dao.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UsersDAO usersDAO;

    @Autowired
    public UserService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }


    public int addNewUser(NewUserDTO newUserDTO){
        return usersDAO.addUser(newUserDTO);
    }
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        usersDAO.getAllUser().forEach(user -> {
            userDTOs.add(new UserDTO(user));
        });
        return userDTOs;
    }
    public UserDTO getUserById(int id){
        return new UserDTO(usersDAO.getUserById(id));
    }
}
