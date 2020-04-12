package com.rc.questionbankservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatus {
    private Long questionStatusId;
    private boolean isVerified;
    private String verifiedDate;
    private boolean isApproved;
    private String approvedDate;
    private String submittedByUserId;
    private String verifiedByUserId;
    private String submittedDate;
    private String approvedByUserId;

}
