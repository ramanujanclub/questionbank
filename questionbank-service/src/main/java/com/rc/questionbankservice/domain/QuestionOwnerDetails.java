package com.rc.questionbankservice.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionOwnerDetails implements Serializable {
    private String name;
    private String emailId;
    private String schoolName;
    private String instituteName;
    private String address;
}
