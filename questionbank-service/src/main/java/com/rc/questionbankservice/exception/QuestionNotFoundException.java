package com.rc.questionbankservice.exception;

import lombok.Getter;

@Getter
public class QuestionNotFoundException extends RuntimeException {

    private String questionId;

    public QuestionNotFoundException(String message, String questionId) {
        super(message);
        this.questionId = questionId;
    }
}
