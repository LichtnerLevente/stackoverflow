package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/answers-for-question/{id}")
    public List<AnswerDTO> getAllAnswersForQuestion(@PathVariable int id) {
        return answerService.getAllAnswersForQuestion(id);
    }

    @GetMapping("/{id}")
    public AnswerDTO getAnswerById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }

    @PostMapping("/create")
    public int addNewAnswer(@RequestBody NewAnswerDTO answer) {
        return answerService.addNewAnswer(answer);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAnswerById(@PathVariable int id) {
        return answerService.deleteAnswerById(id);
    }

    @PatchMapping("/{id}/update")
    public AnswerDTO updateAnswerById(@PathVariable("id") int id, @RequestBody AnswerDTO answerDTO) {
        return answerService.updateAnswer(id, answerDTO);
    }

}
