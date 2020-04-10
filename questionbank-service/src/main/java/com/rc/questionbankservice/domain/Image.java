package com.rc.questionbankservice.domain;

import lombok.Data;

@Data
public class Image {

    private String imageId;
    private String questionId;
    private String parentQuestionId;
    private byte[] questionDescriptionImage;
    private byte[] scannedQuestionImage;
    private byte[] questionHintImage;
    private byte[] parentQuestionImage;
}
