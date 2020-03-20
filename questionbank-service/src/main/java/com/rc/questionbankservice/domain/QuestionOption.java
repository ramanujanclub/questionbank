package com.rc.questionbankservice.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionOption implements Serializable {
    private String optionKey;
    private String optionValue;
}
