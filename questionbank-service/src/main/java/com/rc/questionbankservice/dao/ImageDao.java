package com.rc.questionbankservice.dao;

import com.rc.questionbankservice.entity.ImageEntity;

import java.util.Optional;

public interface ImageDao {
    ImageEntity save(ImageEntity questionEntity);
    Optional<ImageEntity> findByParentQuestionId(final String parentQuestionId);
    Optional<ImageEntity> findByQuestionId(final String questionId);
    Optional<ImageEntity> findByQuestionIdOrParentQuestionId(final String questionId, final String parentQuestionId);

}
