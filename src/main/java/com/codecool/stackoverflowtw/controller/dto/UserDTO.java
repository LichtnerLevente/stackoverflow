package com.codecool.stackoverflowtw.controller.dto;

import java.util.Date;

public record UserDTO(int id, String username, String password, Date created) {
}
