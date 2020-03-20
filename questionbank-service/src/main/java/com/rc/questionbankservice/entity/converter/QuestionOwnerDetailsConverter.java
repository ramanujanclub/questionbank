package com.rc.questionbankservice.entity.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rc.questionbankservice.domain.QuestionOwnerDetails;
import com.rc.questionbankservice.util.QuestionBankUtils;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;


@Converter
@Slf4j
public class QuestionOwnerDetailsConverter implements AttributeConverter<QuestionOwnerDetails, String> {
    @Override
    public String convertToDatabaseColumn(QuestionOwnerDetails questionOwnerDetails) {
        String questionOwnerDetailsAsJson = null;
        try {
            questionOwnerDetailsAsJson = QuestionBankUtils.getObjectMapper().writeValueAsString(questionOwnerDetails);
        } catch (final JsonProcessingException e) {
            log.error("QuestionOwnerDetails JSON writing error", e);
        }
        return questionOwnerDetailsAsJson;
    }

    @Override
    public QuestionOwnerDetails convertToEntityAttribute(String questionOwnerDetailsAsStr) {
        try {
            return QuestionBankUtils.getObjectMapper().readValue(questionOwnerDetailsAsStr, QuestionOwnerDetails.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
