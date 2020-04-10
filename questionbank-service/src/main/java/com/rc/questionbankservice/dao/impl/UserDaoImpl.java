package com.rc.questionbankservice.dao.impl;

import com.rc.questionbankservice.dao.UserDao;
import com.rc.questionbankservice.entity.UserEntity;
import com.rc.questionbankservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository ownerDetailsRepository;

    @Override
    public UserEntity save(UserEntity userEntity) {
        log.info("Saving user to database");
        return ownerDetailsRepository.save(userEntity);
    }

    @Override
    public UserEntity findByUserName(String userName) {
        return ownerDetailsRepository.findByUserName(userName);
    }


}