package com.rc.questionbankservice.service.impl;

import com.rc.questionbankservice.dao.ImageDao;
import com.rc.questionbankservice.dao.ParentQuestionDao;
import com.rc.questionbankservice.dao.QuestionBankDao;
import com.rc.questionbankservice.dao.QuestionStatusDao;
import com.rc.questionbankservice.domain.ApproveQuestionRequest;
import com.rc.questionbankservice.domain.ParentQuestion;
import com.rc.questionbankservice.domain.Question;
import com.rc.questionbankservice.domain.VerifyQuestionRequest;
import com.rc.questionbankservice.entity.ImageEntity;
import com.rc.questionbankservice.entity.ParentQuestionEntity;
import com.rc.questionbankservice.entity.QuestionEntity;
import com.rc.questionbankservice.entity.QuestionStatusEntity;
import com.rc.questionbankservice.exception.QuestionUploadImageException;
import com.rc.questionbankservice.service.QuestionBankService;
import com.rc.questionbankservice.util.ModelMapperUtils;
import com.rc.questionbankservice.util.QuestionBankUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@AllArgsConstructor
@Data
public class QuestionBankServiceImpl implements QuestionBankService {

    private QuestionBankDao questionBankDao;
    private ImageDao imageDao;
    private ParentQuestionDao parentQuestionDao;
    private QuestionStatusDao questionStatusDao;

    @Override
    public Question persistQuestion(Question questionReq, MultipartFile questionContentFile,
                                    MultipartFile scannedQuestionFile) {

        log.info("Converting domain to entity");
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuestionEntity questionEntity = modelMapper.map(questionReq, QuestionEntity.class);
        questionEntity.setQuestionId(UUID.randomUUID().toString());

        QuestionStatusEntity statusEntity = new QuestionStatusEntity();
        // TODO This will be populated from logged user details
        statusEntity.setSubmittedByUserId("aniwesh");
        questionEntity.setQuestionStatusEntity(statusEntity);

        questionEntity = questionBankDao.save(questionEntity);
        saveQuestionImages(questionEntity.getQuestionId(), questionContentFile, scannedQuestionFile);
        Question savedQuestion = ModelMapperUtils.map(questionEntity, Question.class);
        QuestionBankUtils.convertQuestionStatusEntityToQuestionStatus(questionEntity, savedQuestion);
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
    @Override
    public void saveQuestionImages( String questionId, MultipartFile questionContentFile, MultipartFile scannedQuestionFile) {
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
        QuestionBankUtils.convertQuestionStatusEntityToQuestionStatus(questionEntity, question);
        log.info("Found question for questionId : [{}]", questionId);
        return question;
    }

    @Override
    public ParentQuestion persistNewParentQuestion(ParentQuestion parentQuestion) {
        ParentQuestionEntity newParentQuestionEntity = ModelMapperUtils.map(parentQuestion, ParentQuestionEntity.class);
        newParentQuestionEntity.setParentQuestionId(UUID.randomUUID().toString());
        QuestionStatusEntity statusEntity = new QuestionStatusEntity();
        // TODO This will be populated from logged user details
        statusEntity.setSubmittedByUserId("aniwesh");
        newParentQuestionEntity.setQuestionStatusEntity(statusEntity);
        parentQuestionDao.save(newParentQuestionEntity);
        parentQuestion.setParentQuestionId(newParentQuestionEntity.getParentQuestionId());
        return parentQuestion;
    }

    @Override
    public void markQuestionAsVerified(final String questionId, VerifyQuestionRequest verifyQuestionRequest) {
        log.debug("Search question before verifying, questionId : [{}] ", questionBankDao);
        QuestionEntity questionEntity = questionBankDao.findQuestionById(questionId);
        QuestionStatusEntity questionStatusEntity = questionEntity.getQuestionStatusEntity();
        questionStatusEntity.setVerified(true);
        questionStatusEntity.setVerifiedByUserId(verifyQuestionRequest.getVerifiedByUserId());
        questionStatusEntity.setVerifiedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        questionStatusDao.save(questionStatusEntity);
        log.info("Question id [{}] is now verified. VerifiedBy [{}]", questionId, verifyQuestionRequest.getVerifiedByUserId());
    }

    @Override
    public void markQuestionAsApproved(String questionId, ApproveQuestionRequest approveQuestionRequest) {
        log.debug("Search question before approving, questionId : [{}] ", questionBankDao);
        QuestionEntity questionEntity = questionBankDao.findQuestionById(questionId);
        QuestionStatusEntity questionStatusEntity = questionEntity.getQuestionStatusEntity();
        questionStatusEntity.setApproved(true);
        questionStatusEntity.setApprovedByUserId(approveQuestionRequest.getApproveByUserId());
        questionStatusEntity.setApprovedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        questionStatusDao.save(questionStatusEntity);
        log.info("Question id [{}] is now verified. VerifiedBy [{}]", questionId, approveQuestionRequest.getApproveByUserId());

    }


}
