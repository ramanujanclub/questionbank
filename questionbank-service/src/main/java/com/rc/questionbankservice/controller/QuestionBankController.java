package com.rc.questionbankservice.controller;

import com.rc.questionbankservice.domain.QuestionBank;
import com.rc.questionbankservice.service.QuestionBankService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questionbank")
@Slf4j
@AllArgsConstructor
public class QuestionBankController {

    private QuestionBankService questionBankService;

    @GetMapping("health")
    public ResponseEntity checkHealth() {
        return new ResponseEntity("Service is healthy.", HttpStatus.OK);
    }


    /**
     * Persist questions response entity.
     *
     * @return the response entity
     */
    @ApiOperation(value = "Endpoint to persist questions.",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Questions have been saved successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "upload")
    public ResponseEntity persistQuestions(@RequestBody QuestionBank questionBank) throws Exception {

        log.info("Persisting questions: {}", questionBank.getQuestionText());
        questionBankService.persistQuestion(questionBank);
        return new ResponseEntity<String>("Question saved successfully", HttpStatus.CREATED);
    }
}
