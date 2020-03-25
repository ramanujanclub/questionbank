package com.rc.questionbankservice.service.impl;

import com.rc.questionbankservice.dao.QuestionBankDao;
import com.rc.questionbankservice.domain.Question;
import com.rc.questionbankservice.entity.QuestionEntity;
import com.rc.questionbankservice.service.QuestionBankService;
import com.rc.questionbankservice.util.ModelMapperUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
public class QuestionBankServiceImpl implements QuestionBankService {

    private QuestionBankDao questionBankDao;

    @Override
    public Question persistQuestion(Question question, MultipartFile questionContentfile, MultipartFile scannedQuestionFile) throws IOException {
        question.setScannedQuestionFile(scannedQuestionFile.getBytes());
        question.setQuestionDescriptionImage(questionContentfile.getBytes());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        log.info("Converting domain to entity");
        QuestionEntity questionEntity = modelMapper.map(question, QuestionEntity.class);
        questionEntity.setId(UUID.randomUUID().toString());
        questionEntity = questionBankDao.save(questionEntity);
        Question savedQuestion = ModelMapperUtils.map(questionEntity, Question.class);

        log.info("Question Saved : " + questionEntity);
        return savedQuestion;
    }

    @Override
    public List<Question> getAllQuestions() {
        List<QuestionEntity> questionEntities = questionBankDao.getAllQuestions();
        return ModelMapperUtils.mapAll(questionEntities, Question.class);
    }

    @Override
    public Question findQuestionById(final String questionId) {
        log.debug("Searching question by questionId : [{}]", questionId);
        QuestionEntity questionEntity = questionBankDao.findQuestionById(questionId);
        Question question = ModelMapperUtils.map(questionEntity, Question.class);
        log.info("Found question for questionId : [{}]", questionId);
        return question;
    }


}
