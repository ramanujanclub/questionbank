package com.rc.questionbankservice.service.impl;

import com.rc.questionbankservice.dao.QuestionBankDao;
import com.rc.questionbankservice.domain.QuestionBank;
import com.rc.questionbankservice.entity.QuestionEntity;
import com.rc.questionbankservice.service.QuestionBankService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class QuestionBankServiceImpl implements QuestionBankService {

    private QuestionBankDao questionBankDao;

    @Override
    public void persistQuestion(QuestionBank questionBank) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        log.info("Converting domain to entity");
        QuestionEntity questionEntity = modelMapper.map(questionBank, QuestionEntity.class);
        questionEntity.setId(UUID.randomUUID().toString());
        questionEntity = questionBankDao.save(questionEntity);

        log.info("Question Saved : " + questionEntity);
    }


}
