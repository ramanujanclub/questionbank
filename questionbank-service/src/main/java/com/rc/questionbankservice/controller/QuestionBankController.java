package com.rc.questionbankservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rc.questionbankservice.domain.ApproveQuestionRequest;
import com.rc.questionbankservice.domain.ParentQuestion;
import com.rc.questionbankservice.domain.Question;
import com.rc.questionbankservice.domain.SearchQuestionRequest;
import com.rc.questionbankservice.domain.VerifyQuestionRequest;
import com.rc.questionbankservice.service.QuestionBankSearchService;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@CrossOrigin(origins = {"http://localhost:4200"})
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    QuestionBankSearchService questionBankSearchService;

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
    @ApiOperation(value = "Endpoint to find question.",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Question as response."),
            @ApiResponse(code = 500, message = "Internal Server Error."),
            @ApiResponse(code = 404, message = "Question not found.")
    })
    @GetMapping("questions/{questionId}")
    public ResponseEntity<Question> findQuestion(@PathVariable String questionId) {

        log.info("Searching question by questionId : {}", questionId);
        Question question = questionBankService.findQuestionById(questionId);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping("questions/question")
    public ResponseEntity<Question> postNewQuestion(@RequestBody Question questionRequest) {
        log.info("Add new question questionId : {}", questionRequest);
        Question question = questionBankService.persistQuestion(questionRequest,null, null);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @PostMapping("questions/parentquestion")
    public ResponseEntity<ParentQuestion> postNewParentQuestion(@RequestBody ParentQuestion parentQuestionRequest) {

        log.info("Add new parentQuestion  : {}", parentQuestionRequest);
        ParentQuestion newParentQuestion = questionBankService.persistNewParentQuestion(parentQuestionRequest);
        return new ResponseEntity<>(newParentQuestion, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Endpoint to mark question as verified.",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Question has been marked as verified."),
            @ApiResponse(code = 500, message = "Internal Server Error."),
            @ApiResponse(code = 404, message = "Question not found.")
    })
    @PatchMapping("questions/{questionId}/verify")
    public ResponseEntity<String> markQuestionAsVerified(@PathVariable String questionId,
                                                   @RequestBody VerifyQuestionRequest verifyQuestionRequest) {

        log.info("Mark question as verified request received for questionId [{}] by userId: {}", questionId,
                verifyQuestionRequest.getVerifiedByUserId());
        questionBankService.markQuestionAsVerified(questionId, verifyQuestionRequest);
        return new ResponseEntity<>("Question marked as verified.", HttpStatus.OK);
    }


    /**
     *
     * @param questionId
     * @return
     */
    @ApiOperation(value = "Endpoint to mark question as approved.",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Question has been marked as approved."),
            @ApiResponse(code = 500, message = "Internal Server Error."),
            @ApiResponse(code = 404, message = "Question not found.")
    })
    @PatchMapping("questions/{questionId}/approve")
    public ResponseEntity<String> markQuestionAsApproved(@PathVariable String questionId,
                                                    @RequestBody ApproveQuestionRequest approveQuestionRequest) {

        log.info("Mark question as approved request received for questionId [{}] by userId: {}", questionId,
                approveQuestionRequest.getApproveByUserId());
        questionBankService.markQuestionAsApproved(questionId, approveQuestionRequest);
        return new ResponseEntity<>("Question has been marked as approved.", HttpStatus.OK);
    }

    @PostMapping("questions/search")
    public ResponseEntity<List<Question>> searchQuestions(@RequestBody SearchQuestionRequest searchQuestionRequest) {
        log.info("Request received for questionSearch for classIds : [{}] ",
                searchQuestionRequest.getQuestionSearchQuery().getClassId());
        List<Question> matchedQuestions = questionBankSearchService.searchQuestionsByClassId(searchQuestionRequest.getQuestionSearchQuery().getClassId());
        return new ResponseEntity<>(matchedQuestions, HttpStatus.OK);
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
