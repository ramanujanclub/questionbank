package com.rc.questionbankservice.dao.impl;

import com.rc.questionbankservice.dao.QuestionStatusDao;
import com.rc.questionbankservice.entity.QuestionStatusEntity;
import com.rc.questionbankservice.repository.QuestionStatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class QuestionStatusDaoImpl implements QuestionStatusDao {

    @Autowired
    private QuestionStatusRepository questionStatusRepository;

    @Override
    public QuestionStatusEntity save(QuestionStatusEntity questionStatusEntity) {
        log.info("Saving to database");
        return questionStatusRepository.save(questionStatusEntity);
    }


}