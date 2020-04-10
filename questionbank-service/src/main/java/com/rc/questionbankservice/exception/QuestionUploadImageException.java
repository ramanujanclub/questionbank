package com.rc.questionbankservice.exception;

import lombok.Getter;

@Getter
public class QuestionUploadImageException extends RuntimeException {

    public QuestionUploadImageException(String message, Throwable error) {
        super(message, error);
    }
}
