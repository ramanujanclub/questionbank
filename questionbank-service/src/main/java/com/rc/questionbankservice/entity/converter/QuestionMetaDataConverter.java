package com.rc.questionbankservice.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rc.questionbankservice.domain.QuestionMetaData;
import com.rc.questionbankservice.util.QuestionBankUtils;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter
@Slf4j
public class QuestionMetaDataConverter implements AttributeConverter<QuestionMetaData, String> {
    @Override
    public String convertToDatabaseColumn(QuestionMetaData questionMetaData) {
        String questionMetaDataAsJson = null;
        try {
            questionMetaDataAsJson = QuestionBankUtils.getObjectMapper().writeValueAsString(questionMetaData);
        } catch (final JsonProcessingException e) {
            log.error("QuestionMetaData JSON writing error", e);
        }
        return questionMetaDataAsJson;
    }

    @Override
    public QuestionMetaData convertToEntityAttribute(String questionMetaDataAsString) {
        try {
            return QuestionBankUtils.getObjectMapper().readValue(questionMetaDataAsString, QuestionMetaData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }    }
}
