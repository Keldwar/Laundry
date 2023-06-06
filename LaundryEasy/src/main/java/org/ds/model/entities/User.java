package org.ds.model.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    public User() {
    }

    void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
