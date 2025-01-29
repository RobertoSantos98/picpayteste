package com.picpayteste.DTOs;

import java.math.BigDecimal;

import com.picpayteste.Domain.User.UserType;



public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email,  String password, UserType userType) {

}
