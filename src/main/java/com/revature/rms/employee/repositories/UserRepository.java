package com.revature.rms.employee.repositories;


import com.revature.rms.employee.entities.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Integer> {

    /**
     * findAppUserByUsernameAndPassword: The username and password parameters are passed as the input.
     * An appUser is returned when the input username and password Strings match database records.
     * @param username appUser username cred
     * @param password appUser password cred
     * @return appUser with matching username and password Strings
     */
    AppUser findAppUserByUsernameAndPassword(String username, String password);
}
