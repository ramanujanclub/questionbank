package com.rc.questionbankservice.dao;

import com.rc.questionbankservice.entity.QuestionEntity;

import java.util.List;

public interface QuestionBankDao {

    QuestionEntity save(QuestionEntity questionEntity);

    List<QuestionEntity> getAllQuestions();

    QuestionEntity findQuestionById(String questionId);

}
