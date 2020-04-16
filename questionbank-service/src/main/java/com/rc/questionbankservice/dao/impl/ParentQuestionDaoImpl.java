package com.rc.questionbankservice.dao.impl;

import com.rc.questionbankservice.dao.ParentQuestionDao;
import com.rc.questionbankservice.entity.ParentQuestionEntity;
import com.rc.questionbankservice.exception.QuestionNotFoundException;
import com.rc.questionbankservice.repository.ParentQuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ParentQuestionDaoImpl implements ParentQuestionDao {

    private ParentQuestionRepository parentQuestionRepository;

    @Override
    public ParentQuestionEntity save(ParentQuestionEntity parentQuestionEntity) {
        return parentQuestionRepository.save(parentQuestionEntity);
    }

    @Override
    public List<ParentQuestionEntity> findParentQuestionsByUserId(final String userId) {
        return parentQuestionRepository.findByQuestionStatusEntitySubmittedByUserId(userId);
    }

    @Override
    public ParentQuestionEntity findParentQuestionById(String parentQuestionId) {
        Optional<ParentQuestionEntity> entityOptional = parentQuestionRepository.findByParentQuestionId(parentQuestionId);
        if (entityOptional.isPresent()) {
            log.info("Found Parent question, parentQuestionId : [{}] ", parentQuestionId);
            return entityOptional.get();
        }
        throw new QuestionNotFoundException("Question Not found",parentQuestionId);
    }
}