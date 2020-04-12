package com.rc.questionbankservice.service;

import com.rc.questionbankservice.domain.Question;

import java.util.List;

public interface QuestionBankSearchService {
    List<Question> searchQuestionsByClassIds(List<Integer> classIds);
    List<Question> searchQuestionsByClassId(long classId);
}
