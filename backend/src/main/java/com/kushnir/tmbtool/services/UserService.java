package com.kushnir.tmbtool.services;

import com.kushnir.tmbtool.domain.User;
import com.kushnir.tmbtool.exceptions.UsernameAlreadyExistsException;
import com.kushnir.tmbtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {

        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            //Username need to be unique
            newUser.setUsername(newUser.getUsername());

            //Not send confirmPassword to json
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists.");
        }

    }

}
