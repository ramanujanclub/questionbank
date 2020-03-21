package com.rc.questionbankservice.dao.impl;

import com.rc.questionbankservice.dao.QuestionBankDao;
import com.rc.questionbankservice.entity.QuestionEntity;
import com.rc.questionbankservice.exception.QuestionNotFoundException;
import com.rc.questionbankservice.repository.QuestionBankRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class QuestionBankDaoImpl implements QuestionBankDao {

    private QuestionBankRepository questionBankRepository;

    @Override
    public QuestionEntity save(QuestionEntity questionEntity) {
        log.info("Saving to database");
        return questionBankRepository.save(questionEntity);
    }

    @Override
    public List<QuestionEntity> getAllQuestions() {
        log.debug("Getting all the question from database.");
        return questionBankRepository.findAll();
    }

    @Override
    public QuestionEntity findQuestionById(final String questionId) {
        Optional<QuestionEntity> entityOptional = questionBankRepository.findById(questionId);
        if (entityOptional.isPresent()) {
            log.info("Found question, questionId : [{}] ", questionId);
            return entityOptional.get();
        }
        throw new QuestionNotFoundException("Question Not found",questionId);
    }
}