package com.rc.questionbankservice.service.impl;

import com.rc.questionbankservice.dao.QuestionBankDao;
import com.rc.questionbankservice.domain.QuestionBank;
import com.rc.questionbankservice.entity.QuestionBankEntity;
import com.rc.questionbankservice.service.QuestionBankService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class QuestionBankServiceImpl implements QuestionBankService {

    private QuestionBankDao questionBankDao;

    @Override
    public void persistQuestion(QuestionBank questionBank) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        log.info("Converting domain to entity");
        QuestionBankEntity questionBankEntity = modelMapper.map(questionBank, QuestionBankEntity.class);
        questionBankEntity = questionBankDao.save(questionBankEntity);

    }



}
