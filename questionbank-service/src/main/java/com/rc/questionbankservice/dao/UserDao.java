package com.rc.questionbankservice.dao;

import com.rc.questionbankservice.entity.UserEntity;

public interface UserDao {

    UserEntity save(UserEntity userEntity);

    UserEntity findByUserName(String usrName);
}
