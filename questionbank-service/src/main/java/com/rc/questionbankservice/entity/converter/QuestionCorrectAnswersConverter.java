package com.rc.questionbankservice.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rc.questionbankservice.domain.QuestionCorrectAnswers;
import com.rc.questionbankservice.util.QuestionBankUtils;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter
@Slf4j
public class QuestionCorrectAnswersConverter implements AttributeConverter<QuestionCorrectAnswers, String> {

    @Override
    public String convertToDatabaseColumn(QuestionCorrectAnswers questionCorrectAnswers) {
        String questionCorrectAnswersAsJson = null;
        try {
            questionCorrectAnswersAsJson = QuestionBankUtils.getObjectMapper().writeValueAsString(questionCorrectAnswers);
        } catch (final JsonProcessingException e) {
            log.error("QuestionCorrectAnswers JSON writing error", e);
        }
        return questionCorrectAnswersAsJson;
    }

    @Override
    public QuestionCorrectAnswers convertToEntityAttribute(String questionCorrectAnswersStr) {
        try {
            return QuestionBankUtils.getObjectMapper().readValue(questionCorrectAnswersStr, QuestionCorrectAnswers.class);
        } catch (IOException e) {
            log.error("QuestionCorrectAnswers JSON reading error", e);
            e.printStackTrace();
            return null;
        }
    }
}
