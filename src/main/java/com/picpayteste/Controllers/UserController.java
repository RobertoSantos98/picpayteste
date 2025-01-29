package com.picpayteste.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpayteste.DTOs.UserDTO;
import com.picpayteste.Domain.User.User;
import com.picpayteste.Services.UserServices;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices _userServices;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = _userServices.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsuarios(){
        List<User> users = _userServices.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
