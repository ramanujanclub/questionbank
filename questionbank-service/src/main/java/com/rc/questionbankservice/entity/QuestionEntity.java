package com.rc.questionbankservice.entity;

import com.rc.questionbankservice.domain.QuestionCorrectAnswer;
import com.rc.questionbankservice.domain.QuestionHint;
import com.rc.questionbankservice.domain.QuestionMetaData;
import com.rc.questionbankservice.domain.QuestionOptions;
import com.rc.questionbankservice.domain.QuestionOwnerDetails;
import com.rc.questionbankservice.entity.converter.QuestionCorrectAnswersConverter;
import com.rc.questionbankservice.entity.converter.QuestionHintConverter;
import com.rc.questionbankservice.entity.converter.QuestionMetaDataConverter;
import com.rc.questionbankservice.entity.converter.QuestionOptionsConverter;
import com.rc.questionbankservice.entity.converter.QuestionOwnerDetailsConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

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

    @Column(name = "question_options", columnDefinition = "json")
    @Convert(converter= QuestionOptionsConverter.class)
    private QuestionOptions questionOptions;

    @Column(name = "question_correct_answer", columnDefinition = "json")
    @Convert(converter= QuestionCorrectAnswersConverter.class)
    private QuestionCorrectAnswer questionCorrectAnswer;

    @Column(name = "question_complexity_level")
    private String questionComplexityLevel;

    @Column(name = "question_metadata", columnDefinition = "json")
    @Convert(converter= QuestionMetaDataConverter.class)
    private QuestionMetaData questionMetadata;

    @Column(name = "question_owner_details", columnDefinition = "json")
    @Convert(converter= QuestionOwnerDetailsConverter.class)
    private QuestionOwnerDetails questionOwnerDetails;

    @Column(name = "question_hint", columnDefinition = "json")
    @Convert(converter= QuestionHintConverter.class)
    private QuestionHint questionHint;

    @Column(name = "class_id")
    private long classId;

    @Column(name = "chapter_id")
    private long chapterId;

}
