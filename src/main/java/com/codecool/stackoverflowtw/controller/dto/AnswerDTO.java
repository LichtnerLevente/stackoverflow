package com.codecool.stackoverflowtw.controller.dto;

import java.util.Date;

public record AnswerDTO(int id , String answer, int questionId ,Date date, Integer userId) {

}
