package com.soen390.team11.service;

import com.soen390.team11.dto.UserRequestDto;
import com.soen390.team11.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserAccountRepository userAccountRepository;

    public void createUser(UserRequestDto userRequestDto) {
        userAccountRepository.save(userRequestDto.getUserAccount());
        System.out.println(userRequestDto);
    }
}
