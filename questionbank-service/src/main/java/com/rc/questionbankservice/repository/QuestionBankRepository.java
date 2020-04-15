package com.rc.questionbankservice.repository;

import com.rc.questionbankservice.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionBankRepository extends JpaRepository<QuestionEntity, String> {
    List<QuestionEntity> findByClassIdIn(List<Integer> classIds);
    List<QuestionEntity> findByClassId(long classIds);
    List<QuestionEntity> findByQuestionStatusEntitySubmittedByUserId(String userId);
}