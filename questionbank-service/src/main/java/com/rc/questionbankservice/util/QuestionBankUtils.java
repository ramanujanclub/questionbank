package com.rc.questionbankservice.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rc.questionbankservice.domain.ParentQuestion;
import com.rc.questionbankservice.domain.Question;
import com.rc.questionbankservice.domain.QuestionStatus;
import com.rc.questionbankservice.entity.ParentQuestionEntity;
import com.rc.questionbankservice.entity.QuestionEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuestionBankUtils {

    /**
     *
     * @return
     */
    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    public static void convertQuestionStatusEntityToQuestionStatus(QuestionEntity questionEntity, Question question) {
        QuestionStatus questionStatus = ModelMapperUtils.map(questionEntity.getQuestionStatusEntity(), QuestionStatus.class);
        question.setQuestionStatus(questionStatus);
    }

    public static void convertParentQuestionStatusEntityToQuestionStatus(ParentQuestionEntity questionEntity,
                                                                         ParentQuestion parentQuestion) {
        QuestionStatus questionStatus = ModelMapperUtils.map(questionEntity.getQuestionStatusEntity(), QuestionStatus.class);
        parentQuestion.setQuestionStatus(questionStatus);
    }

    public static List<Integer> splitAndConvertToIntegerList(String value) {
        if (StringUtils.isNotEmpty(value)) {
            List<Integer> convertedIntegerList = Stream.of(value.split(",", -1))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return convertedIntegerList;
        }
        return Collections.emptyList();
    }
}
