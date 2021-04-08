package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
import com.soen390.team11.constant.Role;
import com.soen390.team11.dto.UserAccountDto;
import com.soen390.team11.entity.UserAccount;
import com.soen390.team11.repository.UserAccountRepository;
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

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    UserAccountRepository userAccountRepository;
    LogService logService;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserAccountRepository userAccountRepository,
            LogService logService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userAccountRepository = userAccountRepository;
        this.logService = logService;
    }

    /**
     * Creates a new user
     *
     * @param userAccountDto User information
     */
    public void createUser(UserAccountDto userAccountDto) {
        logService.writeLog(LogTypes.USERS, "Creating a new user");
        String role = "";
        if (userAccountDto.getRole() == null) {
            role = Role.CUSTOMER.toString();
        } else {
            role = userAccountDto.getRole().equalsIgnoreCase("admin") ? Role.ADMIN.toString()
                    : Role.CUSTOMER.toString();
        }
        UserAccount userAccount = new UserAccount(userAccountDto.getUsername(),
                bCryptPasswordEncoder.encode(userAccountDto.getPassword()), userAccountDto.getEmail(), role);
        logService.writeLog(LogTypes.USERS, "Saving the new user");
        userAccountRepository.save(userAccount);
    }

    /**
     * the default admin that would display different from user
     */
    @Bean
    public void addAdmin() {
        try {
            logService.writeLog(LogTypes.USERS, "Creating a default admin");
            UserAccount admin = new UserAccount("Admin", bCryptPasswordEncoder.encode("admin"), "admin@erp.com",
                    Role.ADMIN.toString());
            userAccountRepository.save(admin);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logService.writeLog(LogTypes.USERS, "loading the user with the email");
        UserAccount user = userAccountRepository.findByEmail(email);
        if (user == null) {
            new UsernameNotFoundException("user not found");
        }
        logService.writeLog(LogTypes.USERS, "Returning the user email and password");
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    /**
     * get logged user
     * 
     * @return
     */
    public UserAccount getLoggedUser() {
        logService.writeLog(LogTypes.USERS, "Getting the logged user...");
        UserAccount userAccount = userAccountRepository
                .findByEmail(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return userAccount;
    }

    /**
     * get UserAccount by id
     * 
     * @param userid
     * @return
     */
    public UserAccount getUserAccountByUserid(String userid) {
        logService.writeLog(LogTypes.USERS, "Getting the user account using the user ID");
        return userAccountRepository.findByUserID(userid);
    }

    /**
     * get all user except the loggin in user
     * 
     * @return
     */
    public List<UserAccountDto> getAllUser() {
        List<UserAccount> userAccountList = userAccountRepository.findAllByUsernameNot(getLoggedUser().getUsername());
        List<UserAccountDto> userAccountDtoList = new ArrayList<>();
        UserAccountDto userAccountDto = null;
        logService.writeLog(LogTypes.USERS, "Getting all the users except the loggin in user");
        for (UserAccount ua : userAccountList) {
            userAccountDto = new UserAccountDto(ua.getUsername(), ua.getPassword(), ua.getEmail(), ua.getRole(),
                    ua.getUserID());
            userAccountDtoList.add(userAccountDto);
        }
        return userAccountDtoList;
    }

    /**
     * edit user
     * 
     * @param userAccountDto
     * @return
     */
    public UserAccount editUser(UserAccountDto userAccountDto) {
        String role = (userAccountDto.getRole() != null && userAccountDto.getRole().equalsIgnoreCase("admin"))
                ? Role.ADMIN.toString()
                : Role.CUSTOMER.toString();
        String password = userAccountDto.getPassword().startsWith("$2a$10$") ? userAccountDto.getPassword()
                : bCryptPasswordEncoder.encode(userAccountDto.getPassword());

        logService.writeLog(LogTypes.USERS, "Editing the user's information");
        UserAccount olderSet = userAccountRepository.findByUserID(userAccountDto.getUserID());
        UserAccount userAccount = new UserAccount(userAccountDto.getUsername(), password, userAccountDto.getEmail(),
                role);
        userAccount.setUserID(userAccountDto.getUserID());
        logService.writeLog(LogTypes.USERS, "Saving the new user information");
        userAccount.setCustomers(olderSet.getCustomers());
        userAccount.setPayments(olderSet.getPayments());
        return userAccountRepository.save(userAccount);
    }
}