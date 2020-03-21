package com.rc.questionbankservice.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parent_question")
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class ParentQuestion {

    @Id
    private String id;

    @Column(name = "question_description")
    private String questionDescription;

    @Column(name = "question_category")
    private String questionCategory;

    @Column(name = "img_location")
    private String imgLocation;
}
