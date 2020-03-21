package com.rc.questionbankservice.service;

import com.rc.questionbankservice.domain.Question;

import java.util.List;

public interface QuestionBankService {

    Question persistQuestion(Question mgQuestionBank);
    List<Question> getAllQuestions();
    Question findQuestionById(final String questionId);

}
