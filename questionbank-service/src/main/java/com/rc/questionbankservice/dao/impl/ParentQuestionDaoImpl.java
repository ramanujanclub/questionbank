package com.rc.questionbankservice.dao.impl;

import com.rc.questionbankservice.dao.ParentQuestionDao;
import com.rc.questionbankservice.entity.ParentQuestionEntity;
import com.rc.questionbankservice.repository.ParentQuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ParentQuestionDaoImpl implements ParentQuestionDao {
    @Autowired
    private ParentQuestionRepository parentQuestionRepository;
    @Override
    public ParentQuestionEntity save(ParentQuestionEntity parentQuestionEntity) {
        return parentQuestionRepository.save(parentQuestionEntity);
    }
}