package com.progectFood.domian.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @JoinColumn(name="id")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="status")
    @NonNull
    private String status;

}
