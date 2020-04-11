package com.rc.questionbankservice.repository;

import com.rc.questionbankservice.entity.QuestionStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionStatusRepository extends JpaRepository<QuestionStatusEntity, String> {

}