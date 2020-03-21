package com.rc.questionbankservice.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rc.questionbankservice.domain.QuestionCorrectAnswer;
import com.rc.questionbankservice.util.QuestionBankUtils;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter
@Slf4j
public class QuestionCorrectAnswersConverter implements AttributeConverter<QuestionCorrectAnswer, String> {

    @Override
    public String convertToDatabaseColumn(QuestionCorrectAnswer questionCorrectAnswer) {
        String questionCorrectAnswersAsJson = null;
        try {
            questionCorrectAnswersAsJson = QuestionBankUtils.getObjectMapper().writeValueAsString(questionCorrectAnswer);
        } catch (final JsonProcessingException e) {
            log.error("QuestionCorrectAnswers JSON writing error", e);
        }
        return questionCorrectAnswersAsJson;
    }

    @Override
    public QuestionCorrectAnswer convertToEntityAttribute(String questionCorrectAnswersStr) {
        try {
            return QuestionBankUtils.getObjectMapper().readValue(questionCorrectAnswersStr, QuestionCorrectAnswer.class);
        } catch (IOException e) {
            log.error("QuestionCorrectAnswers JSON reading error", e);
            e.printStackTrace();
            return null;
        }
    }
}
