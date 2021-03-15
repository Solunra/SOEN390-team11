package com.soen390.team11.repository;

import com.soen390.team11.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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

}
