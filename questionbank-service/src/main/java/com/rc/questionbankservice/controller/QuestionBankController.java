package com.rc.questionbankservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rc.questionbankservice.domain.Question;
import com.rc.questionbankservice.service.QuestionBankService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/questionbank", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
@AllArgsConstructor
public class QuestionBankController {

    private QuestionBankService questionBankService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("health")
    public ResponseEntity checkHealth() {
        return new ResponseEntity("Service is healthy.", HttpStatus.OK);
    }


    /**
     * Persist questions response entity.
     *
     * @return the response entity
     */
    @ApiOperation(value = "Endpoint to persist question.",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Questions have been saved successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "upload", consumes = { "multipart/form-data", "application/json" })
    public ResponseEntity<Question> persistQuestions(@RequestParam(value = "questionContentfile") MultipartFile questionContentfile,
                                                     @RequestParam(value = "scannedQuestionFile") MultipartFile scannedQuestionFile,
                                                     @RequestParam(value = "question") String question) throws IOException {
        log.info("Persisting questions: {}");
        Question questionResult = questionBankService.persistQuestion(objectMapper.readValue(question, Question.class), questionContentfile, scannedQuestionFile);
        return new ResponseEntity<>(questionResult, HttpStatus.CREATED);
    }

    /**
     *
     * @param questionId
     * @return
     */
    @GetMapping("questions/{questionId}")
    public ResponseEntity<Question> findQuestion(@PathVariable String questionId) {

        log.info("Searching question by questionId : {}", questionId);
        Question question = questionBankService.findQuestionById(questionId);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("questions")
    public ResponseEntity<List<Question>> getQuestionBank() {
        log.info("Getting complete question bank:");
        List<Question> questions = questionBankService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
