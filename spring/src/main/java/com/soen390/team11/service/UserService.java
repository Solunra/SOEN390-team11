package com.soen390.team11.service;

import com.soen390.team11.entity.UserRequestModel;
import com.soen390.team11.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserAccountRepository userAccountRepository;
    public void createUser(UserRequestModel userRequestModel) {
        userAccountRepository.save(userRequestModel.getUserAccount());
        System.out.println(userRequestModel);
    }
}
