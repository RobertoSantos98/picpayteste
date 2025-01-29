package com.picpayteste.Services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpayteste.DTOs.UserDTO;
import com.picpayteste.Domain.User.User;
import com.picpayteste.Domain.User.UserType;
import com.picpayteste.Repositories.UserRepository;

@Service
public class UserServices {

    @Autowired
    private UserRepository repository;

    public void validateTransition(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuários logistas não podem realizar transações");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
            
        }
    }

    public User findUserById(Long id) throws Exception{
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getUsers(){
        List<User> users = this.repository.findAll();
        return users;
    }



}
