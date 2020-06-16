package com.revature.rms.employee.services;

import com.revature.rms.employee.entities.AppUser;
import com.revature.rms.employee.entities.UserRole;
import com.revature.rms.employee.exceptions.ResourceNotFoundException;
import com.revature.rms.employee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository repo) {
        super();
        this.userRepo = repo;
    }

    /**
     * getAllUsers method: Returns a list of all the user objects in the database.
     * @return a list of all the users
     */
    @Transactional(readOnly=true)
    public Iterable<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * findUserById method: Returns an appUser object when the id int matches a record in the database.
     * @param id appUserId int value
     * @return an appUser with matching id
     * @throws RuntimeException when an appUser is not found
     */
    @Transactional(readOnly=true)
    public AppUser findUserById(int id) {
        return userRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    /**
     * authenticate method: Returns an appUser object when the Strings username and password match a record in the database.
     * @param username appUserUsername String value
     * @param password appUserPassword String value
     * @return an appUser with matching Strings
     */
    @Transactional(readOnly=true)
    public AppUser authenticate(String username, String password) {
        AppUser retrievedUser = userRepo.findAppUserByUsernameAndPassword(username, password);
        return retrievedUser;
    }

    /**
     * register method: Takes in an appUser object as the input. Sets the UserRole to a Basic User by default, then persists
     * the newly created appUser into the database.
     * @param newUser newly persisted appUser object
     * @return new appUser
     */
    @Transactional
    public AppUser register(AppUser newUser) {
        newUser.setRole(UserRole.BASIC_USER);
        return userRepo.save(newUser);
    }
}
