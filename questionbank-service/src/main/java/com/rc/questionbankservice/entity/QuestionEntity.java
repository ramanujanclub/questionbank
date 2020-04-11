package com.rc.questionbankservice.entity;

import com.rc.questionbankservice.domain.QuestionCorrectAnswer;
import com.rc.questionbankservice.domain.QuestionHint;
import com.rc.questionbankservice.domain.QuestionMetaData;
import com.rc.questionbankservice.domain.QuestionOptions;
import com.rc.questionbankservice.entity.converter.QuestionCorrectAnswersConverter;
import com.rc.questionbankservice.entity.converter.QuestionHintConverter;
import com.rc.questionbankservice.entity.converter.QuestionMetaDataConverter;
import com.rc.questionbankservice.entity.converter.QuestionOptionsConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "question")
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class QuestionEntity {

    @Id
    @Column(name = "questionid")
    private String questionId;

    @Column(name = "questiondescription")
    private String questionDescription;

    @Column(name = "questionheader")
    private String questionHeader;

    @Column(name = "questionoptions", columnDefinition = "json")
    @Convert(converter = QuestionOptionsConverter.class)
    private QuestionOptions questionOptions;

    @Column(name = "questioncorrectanswer", columnDefinition = "json")
    @Convert(converter = QuestionCorrectAnswersConverter.class)
    private QuestionCorrectAnswer questionCorrectAnswer;

    @Column(name = "questioncomplexitylevel")
    private String questionComplexityLevel;

    @Column(name = "questionmetadata", columnDefinition = "json")
    @Convert(converter = QuestionMetaDataConverter.class)
    private QuestionMetaData questionMetadata;

    @Column(name = "questionhint", columnDefinition = "json")
    @Convert(converter = QuestionHintConverter.class)
    private QuestionHint questionHint;

    @Column(name = "classid")
    private long classId;

    @Column(name = "chapterid")
    private long chapterId;

    @Column(name = "questionnote")
    private String questionNote;

    @Column(name = "parentquestionid")
    private String parentQuestionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionstatusid", referencedColumnName = "questionstatusid")
    private QuestionStatusEntity questionStatusEntity;

}
