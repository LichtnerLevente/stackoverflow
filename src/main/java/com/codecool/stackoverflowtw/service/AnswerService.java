package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

        private AnswersDAO answersDAO;

        @Autowired public AnswerService(AnswersDAO answersDAO) {

            this.answersDAO = answersDAO;
        }

        public List<AnswerDTO> getAllAnswersForQuestion(int questionId) {
            List<AnswerDTO> answersDTO = new ArrayList<>();

            answersDAO.getAllAnswersForQuestions(questionId).forEach(answer ->  {
                answersDTO.add(new AnswerDTO(
                        answer.getId(),
                        answer.getAnswer(),
                        answer.getQuestionId(),
                        answer.getDate(),
                        answer.getUserId()
                ));
            });
            return answersDTO;
        }

        public AnswerDTO getAnswerById(int id) {
            Answer answer = answersDAO.getAnswer(id);
            return new AnswerDTO(
                    answer.getId(),
                    answer.getAnswer(),
                    answer.getQuestionId(),
                    answer.getDate(),
                    answer.getUserId()
            );
        }


        public boolean deleteAnswerById(int id) {
            return answersDAO.deleteAnswerById(id);
        }

        public int addNewAnswer(NewAnswerDTO newAnswerDTO) {
            return answersDAO.addAnswer(newAnswerDTO);
        }

    public AnswerDTO updateAnswer(int id ,AnswerDTO answerDTO) {
        Answer response = answersDAO.updateAnswer(
                new Answer(
                        id,
                        answerDTO.answer(),
                        answerDTO.questionId(),
                        answerDTO.date(),
                        null
                ));
        return new AnswerDTO(response.getId(), response.getAnswer(), response.getQuestionId(), response.getDate(), response.getUserId());
    }


}
