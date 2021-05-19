package com.progectFood.domian.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @JoinColumn(name="id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="role")
    @NonNull
    private String role;
}
