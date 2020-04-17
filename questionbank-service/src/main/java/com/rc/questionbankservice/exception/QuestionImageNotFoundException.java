package com.rc.questionbankservice.exception;

import lombok.Getter;

@Getter
public class QuestionImageNotFoundException extends RuntimeException {

    private String questionId;

    public QuestionImageNotFoundException(String message, String questionId) {
        super(message);
        this.questionId = questionId;
    }
}
