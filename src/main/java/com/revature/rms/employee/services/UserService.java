package com.revature.rms.employee.services;

import com.revature.rms.employee.entities.AppUser;
import com.revature.rms.employee.entities.UserRole;
import com.revature.rms.employee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository repo) {
        super();
        this.userRepo = repo;
    }

    /**
     * getAllUsers method: returns a list of all the user objects in the database.
     * @return a list of all the users
     */
    @Transactional(readOnly=true)
    public Iterable<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Transactional(readOnly=true)
    public AppUser findUserById(int id) {
        return userRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional(readOnly=true)
    public AppUser authenticate(String username, String password) {
        return userRepo.findAppUserByUsernameAndPassword(username, password);
    }

    @Transactional
    public AppUser register(AppUser newUser) {
        newUser.setRole(UserRole.BASIC_USER);
        return userRepo.save(newUser);
    }

}
