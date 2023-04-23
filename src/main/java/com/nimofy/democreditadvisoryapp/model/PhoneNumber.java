package com.nimofy.democreditadvisoryapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

enum Type {
    HOME, WORK, MOBILE
}
@Getter
@Setter
@ToString
@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(nullable = false, name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber number)) return false;
        return id != null && id.equals(number.getId());
    }
}
