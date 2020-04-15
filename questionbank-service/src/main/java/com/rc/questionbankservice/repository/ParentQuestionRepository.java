package com.rc.questionbankservice.repository;

import com.rc.questionbankservice.entity.ParentQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentQuestionRepository extends JpaRepository<ParentQuestionEntity, String> {

    List<ParentQuestionEntity> findByQuestionStatusEntitySubmittedByUserId(String userId);


}