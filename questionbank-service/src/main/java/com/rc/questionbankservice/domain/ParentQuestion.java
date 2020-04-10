package com.rc.questionbankservice.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ParentQuestion {
    private String parentQuestionId;
    private String questionDescription;
    private String questionCategory;
}
