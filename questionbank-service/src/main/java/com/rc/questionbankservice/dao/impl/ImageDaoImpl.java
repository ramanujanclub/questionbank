package com.rc.questionbankservice.dao.impl;

import com.rc.questionbankservice.dao.ImageDao;
import com.rc.questionbankservice.entity.ImageEntity;
import com.rc.questionbankservice.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ImageDaoImpl implements ImageDao {

    private ImageRepository imageRepository;

    @Override
    public ImageEntity save(ImageEntity imageEntity) {
        log.info("Saving to database");
        return imageRepository.save(imageEntity);
    }

    @Override
    public Optional<ImageEntity> findByParentQuestionId(String parentQuestionId) {
        return imageRepository.findByParentQuestionId(parentQuestionId);
    }

    @Override
    public Optional<ImageEntity> findByQuestionId(String questionId) {
        return imageRepository.findByQuestionId(questionId);
    }

    @Override
    public Optional<ImageEntity> findByQuestionIdOrParentQuestionId(String questionId, String parentQuestionId) {
        return imageRepository.findByQuestionIdOrParentQuestionId(questionId, parentQuestionId);
    }


}