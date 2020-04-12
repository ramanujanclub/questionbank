package com.rc.questionbankservice.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class VerifyQuestionRequest {
    private String verifiedByUserId;

    public static void main(String[] args) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        VerifyQuestionRequest verifyQuestionRequest = new VerifyQuestionRequest();
        verifyQuestionRequest.setVerifiedByUserId("aniwesh");

        System.out.println(objectMapper.writeValueAsString(verifyQuestionRequest));

    }
}
