package com.h2demo.h2db.controller;

import com.h2demo.h2db.model.User;
import com.h2demo.h2db.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/greet/{name}")
    public String sayWelcome(@PathVariable String name) {
        return "Heyyyyy " + name + "!" ;
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/login")
    public boolean login(@RequestBody Map<String, String > data) {
        return userService.loginMethod(data.get("email"), data.get("password"));
    }

    @GetMapping("/getUser/{id}")
    public Optional<User> getUser(@PathVariable int  id){
        return userService.getUser(id);
    }

    @GetMapping("/findUser/{id}")
    public String findUser(@PathVariable int id){
        return userService.getUser(id).isPresent() ? id + " exists" : "no such user exists";
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public Optional<User> updateUser(@PathVariable int id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @PostMapping("/signup")
    public String signup(@Valid  @RequestBody User user) {
        userService.addUser(user);
        return "New user added!" ;
    }
}
