package com.progectFood.domian.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@MappedSuperclass
public class EntityBase {
    @Id
    @JoinColumn(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}
