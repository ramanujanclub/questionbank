package com.rc.questionbankservice.exception;

import com.rc.questionbankservice.domain.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public final ResponseEntity<ErrorModel> handleQuestionNotFoundException(QuestionNotFoundException questionNotFoundException,
                                                                            WebRequest webRequest) {

        log.warn("Question Not found. QuestionId : [{}]", questionNotFoundException.getQuestionId());
        ErrorModel errorModel = ErrorModel.builder().errorCode("404").
                errorMessage(questionNotFoundException.getMessage()).build();

        return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
    }
}
