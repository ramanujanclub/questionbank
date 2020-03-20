package com.rc.questionbankservice.entity;

import com.rc.questionbankservice.domain.*;
import com.rc.questionbankservice.entity.converter.*;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "question")
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class QuestionEntity {

    @Id
    private String id;

    @Column(name = "question_description")
    private String questionDescription;

    //@Type(type = "json")
    @Column(name = "question_options", columnDefinition = "json")
    @Convert(converter= QuestionOptionsConverter.class)
    private QuestionOptions questionOptions;

    //@Type(type = "json")
    @Column(name = "question_correct_answer", columnDefinition = "json")
    @Convert(converter= QuestionCorrectAnswersConverter.class)
    private QuestionCorrectAnswers questionCorrectAnswer;

    @Column(name = "question_complexity_level")
    private String questionComplexityLevel;

    //@Type(type = "json")
    @Column(name = "question_metadata", columnDefinition = "json")
    @Convert(converter= QuestionMetaDataConverter.class)
    private QuestionMetaData questionMetadata;

    //@Type(type = "json")
    @Column(name = "question_owner_details", columnDefinition = "json")
    @Convert(converter= QuestionOwnerDetailsConverter.class)
    private QuestionOwnerDetails questionOwnerDetails;

    //@Type(type = "json")
    @Column(name = "question_hint", columnDefinition = "json")
    @Convert(converter= QuestionHintConverter.class)
    private QuestionHint questionHint;

    @Column(name = "class_id")
    private long classId;

    @Column(name = "chapter_id")
    private long chapterId;

}
