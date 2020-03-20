package com.rc.questionbankservice.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rc.questionbankservice.domain.QuestionHint;
import com.rc.questionbankservice.domain.QuestionOptions;
import com.rc.questionbankservice.util.QuestionBankUtils;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter
@Slf4j
public class QuestionHintConverter implements AttributeConverter<QuestionHint, String> {
    @Override
    public String convertToDatabaseColumn(QuestionHint questionHint) {
        String questionHintAsJson = null;
        try {
            questionHintAsJson = QuestionBankUtils.getObjectMapper().writeValueAsString(questionHint);
        } catch (final JsonProcessingException e) {
            log.error("QuestionHint JSON writing error", e);
        }
        return questionHintAsJson;
    }

    @Override
    public QuestionHint convertToEntityAttribute(String questionHintAsString) {
        try {
            return QuestionBankUtils.getObjectMapper().readValue(questionHintAsString, QuestionHint.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }    }
}
