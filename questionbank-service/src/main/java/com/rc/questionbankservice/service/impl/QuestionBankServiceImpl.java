package com.rc.questionbankservice.service.impl;

import com.rc.questionbankservice.dao.ImageDao;
import com.rc.questionbankservice.dao.ParentQuestionDao;
import com.rc.questionbankservice.dao.QuestionBankDao;
import com.rc.questionbankservice.dao.UserDao;
import com.rc.questionbankservice.domain.ParentQuestion;
import com.rc.questionbankservice.domain.Question;
import com.rc.questionbankservice.domain.QuestionOwnerDetails;
import com.rc.questionbankservice.entity.ImageEntity;
import com.rc.questionbankservice.entity.ParentQuestionEntity;
import com.rc.questionbankservice.entity.QuestionEntity;
import com.rc.questionbankservice.entity.QuestionStatusEntity;
import com.rc.questionbankservice.exception.QuestionUploadImageException;
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
    private ImageDao imageDao;
    private UserDao userDao;
    private ParentQuestionDao parentQuestionDao;

    @Override
    public Question persistQuestion(Question question, MultipartFile questionContentFile,
                                    MultipartFile scannedQuestionFile) {

        log.info("Converting domain to entity");
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuestionEntity questionEntity = modelMapper.map(question, QuestionEntity.class);
        questionEntity.setQuestionId(UUID.randomUUID().toString());

        QuestionStatusEntity statusEntity = new QuestionStatusEntity();
        statusEntity.setSubmittedByUserId("aniwesh");
        questionEntity.setQuestionStatusEntity(statusEntity);

        questionEntity = questionBankDao.save(questionEntity);

        saveQuestionImages(questionEntity.getQuestionId(), questionContentFile, scannedQuestionFile);
        Question savedQuestion = ModelMapperUtils.map(questionEntity, Question.class);

        log.info("Question Saved : " + questionEntity);
        return savedQuestion;
    }

    /**
     *
     * @param questionId
     * @param questionContentFile
     * @param scannedQuestionFile
     * @return
     */
    private void saveQuestionImages( String questionId, MultipartFile questionContentFile, MultipartFile scannedQuestionFile) {
        log.debug("Saving images.");
        boolean found = false;
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setQuestionId(questionId);

        try {
            if (questionContentFile != null) {
                imageEntity.setQuestionDescriptionImage(questionContentFile.getBytes());
                found = true;
            }
            if (scannedQuestionFile != null) {
                imageEntity.setScannedQuestionImage(scannedQuestionFile.getBytes());
                found = true;
            }
            if (found) {
                imageEntity.setImageId(UUID.randomUUID().toString());
                imageEntity = imageDao.save(imageEntity);
                log.info("Question image saved. QuestionId [{}] and imageId [{}]", questionId, imageEntity.getImageId());
            }
        } catch (IOException e) {
            log.error("Error while saving images. Error message: "+ e.getMessage());
            throw new QuestionUploadImageException("Unable to save image", e);
        }

    }

    private String findOwnerIdByName(QuestionOwnerDetails questionOwnerDetails) {

        return null;
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

    @Override
    public ParentQuestion persistNewParentQuestion(ParentQuestion parentQuestion) {
        ParentQuestionEntity newParentQuestionEntity = ModelMapperUtils.map(parentQuestion, ParentQuestionEntity.class);
        newParentQuestionEntity.setParentQuestionId(UUID.randomUUID().toString());
        parentQuestionDao.save(newParentQuestionEntity);
        parentQuestion.setParentQuestionId(newParentQuestionEntity.getParentQuestionId());
        return parentQuestion;
    }


}
