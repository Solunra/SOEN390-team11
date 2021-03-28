package com.soen390.team11.repository;

import com.soen390.team11.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for User Account
 */
@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, String> {
    /**
     * Find a user by its email
     *
     * @param email The user's email
     * @return The user account details
     */
    UserAccount findByEmail(String email);

    /**
     * find all user that unername not the input parameter
     * @param username
     * @return
     */
    List<UserAccount> findAllByUsernameNot(String username);

    /**
     * get user by id
     * @param userid
     * @return
     */
    UserAccount findByUserID(String userid);
}
