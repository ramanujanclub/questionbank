package com.rc.questionbankservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Data
public class QuestionBank {
    private String questionDescription;
    private QuestionOptions questionOptions;
    private QuestionCorrectAnswers questionCorrectAnswers;
    private long chapterId;
    private long classId;
    private String questionComplexityLevel;
    private QuestionMetaData questionMetadata;
    private QuestionOwnerDetails questionOwnerDetails;
    private QuestionHint questionHint;
/*
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            QuestionBank questionBank = new QuestionBank();
            questionBank.setQuestionDescription("10 + 20 = ?");
            questionBank.setChapterId(1);
            questionBank.setClassId(1);
            questionBank.setQuestionComplexityLevel("Direct");

            QuestionOption questionOption = new QuestionOption();
            questionOption.setOptionKey("1");
            questionOption.setOptionValue("ABC");
            QuestionOption questionOption1 = new QuestionOption();
            questionOption1.setOptionKey("2");
            questionOption1.setOptionValue("XYZ");

            QuestionOptions questionOptions = new QuestionOptions();
            questionOptions.setMultipleQuestionOption(new ArrayList<>(3));
            questionOptions.getMultipleQuestionOption().add(questionOption);
            questionOptions.getMultipleQuestionOption().add(questionOption1);
            questionBank.setQuestionOptions(questionOptions);

            CorrectAnswer correctAnswer = new CorrectAnswer();
            correctAnswer.setAnswerKey("1");
            correctAnswer.setAnswerValue("ABC");
            QuestionCorrectAnswers questionCorrectAnswers = new QuestionCorrectAnswers();
            questionCorrectAnswers.setCorrectAnswers(new ArrayList<>(1));
            questionCorrectAnswers.getCorrectAnswers().add(correctAnswer);

            questionBank.setQuestionCorrectAnswers(questionCorrectAnswers);

            QuestionOwnerDetails questionOwnerDetails = new QuestionOwnerDetails();
            questionOwnerDetails.setEmailId("aniwesh@abc.com");
            questionOwnerDetails.setName("Aniwesh");
            questionBank.setQuestionOwnerDetails(questionOwnerDetails);

            QuestionHint questionHint = new QuestionHint();
            questionHint.setHint("Add given number in question.");
            questionBank.setQuestionHint(questionHint);
            System.out.println(objectMapper.writeValueAsString(questionBank));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

 */
}
