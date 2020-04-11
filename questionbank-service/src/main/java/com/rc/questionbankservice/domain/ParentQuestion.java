package com.rc.questionbankservice.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ParentQuestion {
    private String parentQuestionId;
    private String questionDescription;
    private String questionCategory;
    private String questionHeader;
    private String questionNote;
    private String questionComplexityLevel;
    private int classId;
    private byte[] questionDescriptionImage;
    private byte[] scannedQuestionImage;
}
