package com.rc.questionbankservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "image")
public class ImageEntity {

    @Id
    private String imageId;

    @Column(name = "questionDescriptionImage", columnDefinition = "mediumblob")
    @Lob
    private byte[] questionDescriptionImage;

    @Column(name = "scannedQuestionImage", columnDefinition = "mediumblob")
    @Lob
    private byte[] scannedQuestionImage;

    @Column(name = "questionHintImage", columnDefinition = "mediumblob")
    @Lob
    private byte[] questionHintImage;

    @Column(name = "parentQuestionImage", columnDefinition = "mediumblob")
    @Lob
    private byte[] parentQuestionImage;

    @Column(name = "questionId")
    private String questionId;

    @Column(name = "parentQuestionId")
    private String parentQuestionId;
}
