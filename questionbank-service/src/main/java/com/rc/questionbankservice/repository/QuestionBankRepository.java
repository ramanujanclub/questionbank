package com.rc.questionbankservice.repository;

import com.rc.questionbankservice.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionBankRepository extends JpaRepository<QuestionEntity, String> {

}