package com.nimofy.democreditadvisoryapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@MappedSuperclass
@EqualsAndHashCode(of = {"username", "email"})
public abstract class User {
    @Column(nullable = false, name = "username", unique = true)
    private String username;

    @Column(nullable = false, name = "email", unique = true)
    private String email;
}
