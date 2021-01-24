package com.soen390.team11.service;

import com.soen390.team11.dto.UserRequestDto;
import com.soen390.team11.entity.UserAccount;
import com.soen390.team11.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(UserRequestDto userRequestDto) {
        UserAccount userAccount=  new UserAccount(userRequestDto.getUsername(),
                bCryptPasswordEncoder.encode(userRequestDto.getPassword()),
                userRequestDto.getEmail());
        userAccountRepository.save(userAccount);
    }
}
