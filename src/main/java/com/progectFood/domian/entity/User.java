package com.progectFood.domian.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="users", uniqueConstraints={@UniqueConstraint(columnNames = "login")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="id")
    private Integer id;


    @Column(name="firstname")
    @NonNull
    private String firstName;

    @Column(name="lastname")
    @NonNull
    private String lastName;

    @Column(name="phone")
    @NonNull
    private String phone;

    @Column(name="login")
    @NonNull
    private String login;

    @Column(name="password")
    @NonNull
    private String password;

    @ManyToOne
    @JoinColumn(name="role")
    private Role role;

    @ManyToOne
    @JoinColumn(name="status")
    private Status status;


}