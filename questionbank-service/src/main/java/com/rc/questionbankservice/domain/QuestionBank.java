package com.rc.questionbankservice.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuestionBank {

    private String questionText;
    private String questionOptions;
    private String questionCorrectAnswer;
    private int chapterId;
    private int classId;
    private String questionComplexityLevel;
    private String questionMetadata;
    private String questionOwnerDetails;
    private String questionHint;
    private MultipartFile questionImage;
}
