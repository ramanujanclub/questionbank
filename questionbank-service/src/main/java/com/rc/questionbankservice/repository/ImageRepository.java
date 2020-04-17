package com.rc.questionbankservice.repository;

import com.rc.questionbankservice.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {

    Optional<ImageEntity> findByParentQuestionId(final String parentQuestionId);
    Optional<ImageEntity> findByQuestionId(final String questionId);
    Optional<ImageEntity> findByQuestionIdOrParentQuestionId(final String questionId, final String parentQuestionId);

}