package com.rc.questionbankservice.dao;

import com.rc.questionbankservice.entity.ParentQuestionEntity;

import java.util.List;

public interface ParentQuestionDao {

    ParentQuestionEntity save(ParentQuestionEntity parentQuestionEntity);
    List<ParentQuestionEntity> findParentQuestionsByUserId(String userId);
    ParentQuestionEntity findParentQuestionById(String parentQuestionId);


}
