package com.soen390.team11.service;

import com.soen390.team11.constant.Role;
import com.soen390.team11.dto.UserSignUpRequestDto;
import com.soen390.team11.entity.UserAccount;
import com.soen390.team11.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Layer for Users
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserAccountRepository userAccountRepository;

    /**
     * Creates a new user
     *
     * @param userSignUpRequestDto User information
     */
    public void createUser(UserSignUpRequestDto userSignUpRequestDto){
        UserAccount userAccount=  new UserAccount(userSignUpRequestDto.getUsername(),
                bCryptPasswordEncoder.encode(userSignUpRequestDto.getPassword()),
                userSignUpRequestDto.getEmail(), Role.CUSTOMER.toString());
        userAccountRepository.save(userAccount);
    }

    /**
     * the default admin that would display different from user
     */
    @Bean
    public void addAdmin(){
        try{
            UserAccount admin=  new UserAccount("Admin",
                    bCryptPasswordEncoder.encode("admin"),
                    "admin@erp.com", Role.ADMIN.toString());
            userAccountRepository.save(admin);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findByEmail(email);
        if(user == null) {
            new UsernameNotFoundException("user not found");
        }
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    /**
     * get logged user
     * @return
     */
    public UserAccount getLoggedUser(){
        UserAccount userAccount= userAccountRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return userAccount;
    }

    /**
     * get UserAccount by id
     * @param userid
     * @return
     */
    public UserAccount getUserAccountByUserid(String userid){
        return userAccountRepository.findByUserID(userid);
    }

    /**
     * get all user except the loggin in user
     * @return
     */
    public List<UserAccount> getAllUser() {
        List<UserAccount> userAccountList= userAccountRepository.findAllByUsernameNot(getLoggedUser().getUsername());
        return userAccountList;
    }

    /**
     * edit user
     * @param userSignUpRequestDto
     * @return
     */
    public UserAccount editUser(UserSignUpRequestDto userSignUpRequestDto) {
        String role = (userSignUpRequestDto.getRole() !=null &&userSignUpRequestDto.getRole().equalsIgnoreCase("admin"))?Role.ADMIN.toString(): Role.CUSTOMER.toString();
        String password = userSignUpRequestDto.getPassword().startsWith("$2a$10$")? userSignUpRequestDto.getPassword() : bCryptPasswordEncoder.encode(userSignUpRequestDto.getPassword());
        UserAccount userAccount=  new UserAccount(userSignUpRequestDto.getUsername(),
                password,
                userSignUpRequestDto.getEmail(),role,userSignUpRequestDto.getUserID());
        return userAccountRepository.save(userAccount);
    }
}
