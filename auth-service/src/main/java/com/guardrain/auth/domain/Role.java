package com.guardrain.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Getter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true, length = 20)
    private String name;

    @Column(length = 100)
    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
