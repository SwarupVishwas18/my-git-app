package com.h2demo.h2db.service;

import com.h2demo.h2db.model.User;
import com.h2demo.h2db.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public boolean loginMethod(String email, String password){

       return userRepository.findUserForLogin(email, password).isPresent();
    }

    public Optional<User> getUser (int id){
        return userRepository.findById(id);
    }

    public boolean deleteUser (int id){
        Optional<User> userFound = userRepository.findById(id);
        if(userFound.isEmpty()) return false;
        userRepository.delete(userFound.get());
        return true;
    }

    public Optional<User> updateUser (int id, User newUser){
        Optional<User> oldUserOpt = userRepository.findById(id);
        if(oldUserOpt.isPresent()){
            User oldUser = oldUserOpt.get();
            oldUser.setCity(newUser.getCity());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setUsername(newUser.getUsername());
            userRepository.save(oldUser);
            return Optional.of(oldUser);
        }

       return Optional.empty();

    }
}
