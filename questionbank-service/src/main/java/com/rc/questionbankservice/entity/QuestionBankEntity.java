package com.rc.questionbankservice.entity;

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
@Entity
@TypeDef(name = "json", typeClass = JsonBinaryType.class)
public class QuestionBankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String questionText;

    @Type(type = "json")
    @Column(name = "options", columnDefinition = "json")
    private String questionOptions;

    @Type(type = "json")
    @Column(name = "answer", columnDefinition = "json")
    private String questionCorrectAnswer;

    @Column(name = "complexity_level")
    private String questionComplexityLevel;

    @Type(type = "json")
    @Column(name = "metadata", columnDefinition = "json")
    private String questionMetadata;

    @Type(type = "json")
    @Column(name = "owner_details", columnDefinition = "json")
    private String questionOwnerDetails;

    @Type(type = "json")
    @Column(name = "hint", columnDefinition = "json")
    private String questionHint;

    @Column(name = "class_id")
    private int classId;

    @Column(name = "chapter_id")
    private int chapterId;

}
