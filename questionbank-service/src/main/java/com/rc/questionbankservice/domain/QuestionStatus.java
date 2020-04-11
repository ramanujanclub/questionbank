package com.rc.questionbankservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionStatus {
    private Long questionStatusId;
    private boolean isVerified;
    private Timestamp verifiedDate;
    private boolean isApproved;
    private Timestamp approvedDate;
    private String submittedByUserId;
    private String verifiedByUserId;
    private Timestamp submittedDate;
    private String approvedByUserId;

}
