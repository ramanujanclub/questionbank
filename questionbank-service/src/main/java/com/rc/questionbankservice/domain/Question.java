package com.rc.questionbankservice.domain;

import lombok.Data;

@Data
public class Question {
    private String questionId;
    private String parentQuestionId;
    private String questionDescription;
    private String questionHeader;
    private String questionNote;
    private byte[] questionDescriptionImage;
    private byte[] scannedQuestionImage;
    private QuestionOptions questionOptions;
    private QuestionCorrectAnswer questionCorrectAnswer;
    private long chapterId;
    private long classId;
    private String questionComplexityLevel;
    private QuestionMetaData questionMetadata;
    private QuestionHint questionHint;
    private QuestionStatus questionStatus;

/*
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Question questionBank = new Question();
            questionBank.setQuestionHeader("Memory Stick");
            questionBank.setQuestionDescription("This is question description");
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
            QuestionCorrectAnswer questionCorrectAnswers = new QuestionCorrectAnswer();
            questionCorrectAnswers.setCorrectAnswers(new ArrayList<>(1));
            questionCorrectAnswers.getCorrectAnswers().add(correctAnswer);

            questionBank.setQuestionCorrectAnswer(questionCorrectAnswers);

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
