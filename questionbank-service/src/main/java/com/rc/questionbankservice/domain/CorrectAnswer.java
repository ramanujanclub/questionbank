package com.rc.questionbankservice.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class CorrectAnswer implements Serializable {
    private String answerKey;
    private String answerValue;
}
