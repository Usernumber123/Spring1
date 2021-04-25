package com.progectFood.domian.dto;

import com.progectFood.validator.CustomPhone;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    @NotNull
    @NotEmpty(message = "firstName is Empty")
    private String firstName;
    @NotNull
    @NotEmpty(message = "lastName is Empty")
    private String lastName;
    @CustomPhone
    private String phone;
    @Min(value=5 , message = "login is short")
    private String login;
    @NotNull
    @NotEmpty(message = "password is Empty")
    @Min(value=5 , message = "password is short")
    private String password;
}
