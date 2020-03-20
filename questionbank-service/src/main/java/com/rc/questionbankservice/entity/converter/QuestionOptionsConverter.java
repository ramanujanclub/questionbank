package com.rc.questionbankservice.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rc.questionbankservice.domain.QuestionOptions;
import com.rc.questionbankservice.util.QuestionBankUtils;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter
@Slf4j
public class QuestionOptionsConverter implements AttributeConverter<QuestionOptions, String> {

    @Override
    public String convertToDatabaseColumn(QuestionOptions questionOptions) {
        String questionOptionsAsJson = null;
        try {
            questionOptionsAsJson = QuestionBankUtils.getObjectMapper().writeValueAsString(questionOptions);
        } catch (final JsonProcessingException e) {
            log.error("QuestionOptions JSON writing error", e);
        }
        return questionOptionsAsJson;
    }

    @Override
    public QuestionOptions convertToEntityAttribute(String questionOptionsStr) {
        try {
            return QuestionBankUtils.getObjectMapper().readValue(questionOptionsStr, QuestionOptions.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
