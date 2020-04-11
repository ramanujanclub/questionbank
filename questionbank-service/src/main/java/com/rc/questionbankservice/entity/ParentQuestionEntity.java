package com.rc.questionbankservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parent_question")
public class ParentQuestionEntity {

    @Id
    @Column(name = "parentquestionid")
    private String parentQuestionId;

    @Column(name = "parentquestionheader")
    private String questionHeader;

    @Column(name = "questiondescription")
    private String questionDescription;

    @Column(name = "questionnote")
    private String questionNote;

    @Column(name = "questioncomplexitylevel")
    private String questionComplexityLevel;

    @Column(name = "classid")
    private int classId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionstatusid", referencedColumnName = "questionstatusid")
    private QuestionStatusEntity questionStatusEntity;
}
