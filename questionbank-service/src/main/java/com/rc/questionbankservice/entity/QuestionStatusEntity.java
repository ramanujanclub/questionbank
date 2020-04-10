package com.rc.questionbankservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "question_status")
public class QuestionStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionStatusId")
    private String questionStatusId;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "isVerified")
    private boolean isVerified;

    @Column(name = "verifiedBy")
    private String verifiedBy;

    @Column(name = "verifiedDate")
    private Timestamp verifiedDate;

    @Column(name = "isApproved")
    private boolean isApproved;

    @Column(name = "approvedBy")
    private String approvedBy;

    @Column(name = "approvedDate")
    private Timestamp approvedDate;
}
