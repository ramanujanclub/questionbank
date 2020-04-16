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
    @Column (name = "imageid")
    private String imageId;

    @Column(name = "questiondescriptionimage", columnDefinition = "mediumblob")
    @Lob
    private byte[] questionDescriptionImage;

    @Column(name = "scannedquestionimage", columnDefinition = "mediumblob")
    @Lob
    private byte[] scannedQuestionImage;

    @Column(name = "questionhintimage", columnDefinition = "mediumblob")
    @Lob
    private byte[] questionHintImage;

    @Column(name = "parentquestionimage", columnDefinition = "mediumblob")
    @Lob
    private byte[] parentQuestionImage;

    @Column(name = "questionid")
    private String questionId;

    @Column(name = "parentquestionid")
    private String parentQuestionId;
}
