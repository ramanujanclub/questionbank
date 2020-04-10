package com.rc.questionbankservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parent_question")
public class ParentQuestionEntity {

    @Id
    @Column(name = "parentquestionid")
    private String parentQuestionId;

    @Column(name = "questiondescription")
    private String questionDescription;

    @Column(name = "questioncategory")
    private String questionCategory;

}
