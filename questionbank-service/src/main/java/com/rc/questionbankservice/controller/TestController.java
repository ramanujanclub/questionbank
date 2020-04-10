package com.rc.questionbankservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rc.questionbankservice.domain.Question;
import com.rc.questionbankservice.service.QuestionBankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

@Controller
@RequestMapping(value = "/api/v1/questiongenerator", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
@AllArgsConstructor
public class TestController {

    private QuestionBankService questionBankService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("health")
    public ResponseEntity checkHealth() {
        return new ResponseEntity("Service is healthy.", HttpStatus.OK);
    }


    /**
     *
     * @param questionId
     * @return
     */
    @GetMapping("/questions/{questionId}")
    public ModelAndView findQuestion(@PathVariable String questionId) {

        log.info("Searching question by questionId : {}", questionId);
        Question question = questionBankService.findQuestionById(questionId);
        byte[] imageBytes = question.getScannedQuestionImage();
        ModelAndView mav = new ModelAndView("generatedQuestion");
        mav.addObject("question", question);
        mav.addObject("pic", Base64.getEncoder().encodeToString(imageBytes));
        mav.addObject("questionContent", Base64.getEncoder().encodeToString(question.getQuestionDescriptionImage()));

        return mav;
    }
}
