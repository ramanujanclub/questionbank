package com.rc.questionbankservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "question_status")
public class QuestionStatusEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "questionstatusid")
    private Long questionStatusId;

    @Column(name = "submittedbyuserid")
    private String submittedByUserId;

    @Column(name = "submitteddate")
    @CreationTimestamp
    private Timestamp submittedDate;

    @Column(name = "isverified")
    private boolean isVerified;

    @Column(name = "verifiedbyuserid")
    private String verifiedByUserId;

    @Column(name = "verifieddate")
    private Timestamp verifiedDate;

    @Column(name = "isapproved")
    private boolean isApproved;

    @Column(name = "approvedbyuserid")
    private String approvedByUserId;

    @Column(name = "approveddate")
    private Timestamp approvedDate;

    @OneToOne(mappedBy = "questionStatusEntity")
    private QuestionEntity questionEntity;

    @OneToOne(mappedBy = "questionStatusEntity")
    private ParentQuestionEntity parentquestionid;

}
