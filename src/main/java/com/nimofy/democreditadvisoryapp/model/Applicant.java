package com.nimofy.democreditadvisoryapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@ToString(exclude = {"applications", "phoneNumbers"})
@Entity
@Table(name = "applicants")
public class Applicant extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "social_security_number")
    @NaturalId
    private String ssn;
    @OneToMany(mappedBy = "applicant", fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "applicant", fetch = FetchType.LAZY, orphanRemoval = true, cascade = ALL)
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();
    @Embedded
    private Address address;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant applicant)) return false;
        return id != null && id.equals(applicant.getId());
    }

    public void addApplication(Application application) {
        application.setApplicant(this);
        applications.add(application);
    }

    public void removeApplication(Application application) {
        application.setApplicant(null);
        applications.remove(application);
    }
}
