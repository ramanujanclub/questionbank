package com.rc.questionbankservice.service.impl;

import com.rc.questionbankservice.dao.QuestionBankDao;
import com.rc.questionbankservice.domain.Question;
import com.rc.questionbankservice.domain.QuestionStatus;
import com.rc.questionbankservice.entity.QuestionEntity;
import com.rc.questionbankservice.service.QuestionBankSearchService;
import com.rc.questionbankservice.util.ModelMapperUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionBankSearchServiceImpl implements QuestionBankSearchService {

    @Autowired
    private QuestionBankDao questionBankDao;

    @Override
    public List<Question> searchQuestionsByClassIds(List<Integer> classIds) {
        List<QuestionEntity> questionEntityList = questionBankDao.findQuestionByClassIds(classIds);
        List<Question> matchedQuestions = getApprovedQuestionsFromQuestionEntities(questionEntityList);
        return matchedQuestions;
    }

    @Override
    public List<Question> searchQuestionsByClassId(long classId) {
        List<QuestionEntity> questionEntityList = questionBankDao.findAllQuestionsByClassId(classId);
        List<Question> matchedQuestions = getApprovedQuestionsFromQuestionEntities(questionEntityList);
        return matchedQuestions;
    }

    /**
     *
     * @param questionEntityList
     * @return
     */
    private List<Question> getApprovedQuestionsFromQuestionEntities(List<QuestionEntity> questionEntityList) {
        if (CollectionUtils.isNotEmpty(questionEntityList)) {
            List<Question> matchedQuestions = new ArrayList<>(questionEntityList.size());
            for (QuestionEntity questionEntity : questionEntityList) {
                if (questionEntity.getQuestionStatusEntity().isApproved()) {
                    Question tempQuestion = ModelMapperUtils.map(questionEntity, Question.class);
                    QuestionStatus questionStatus = ModelMapperUtils.map(questionEntity.getQuestionStatusEntity(), QuestionStatus.class);
                    tempQuestion.setQuestionStatus(questionStatus);
                    matchedQuestions.add(tempQuestion);
                }
            }
            log.debug("Matched questions : [{}]", matchedQuestions);
            return matchedQuestions;
        }
        return Collections.emptyList();
    }
}
