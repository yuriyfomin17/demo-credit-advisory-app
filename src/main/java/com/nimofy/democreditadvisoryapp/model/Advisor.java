package com.nimofy.democreditadvisoryapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

enum Role {
    ASSOCIATE, PARTNER, SENIOR
}

@Getter
@Setter
@ToString(exclude = "applications")
@Entity
@Table(name = "advisors")
public class Advisor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private Role role;

    @OneToMany(mappedBy = "advisor", cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER)
    private List<Application> applications = new ArrayList<>();

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advisor advisor)) return false;
        return id != null && id.equals(advisor.getId());
    }
    public boolean isApplicationAssigned(){
        return this.applications.stream()
                .anyMatch(application -> application.getStatus().equals(Status.ASSIGNED));
    }

    public void addApplication(Application application) {
        if (application.getStatus().equals(Status.ASSIGNED)){
            throw new RuntimeException("Application is already assigned");
        }
        application.setAdvisor(this);
        applications.add(application);
    }

    public void removeApplication(Application application) {
        application.setAdvisor(null);
        applications.remove(application);
    }

    public BigDecimal getLowerBound() {
        return switch (this.role) {
            case ASSOCIATE -> BigDecimal.ZERO;
            case PARTNER -> BigDecimal.valueOf(10_000);
            case SENIOR -> BigDecimal.valueOf(50_000);
        };
    }

    public BigDecimal getUpperBound() {
        return switch (this.role) {
            case ASSOCIATE -> BigDecimal.valueOf(10_000);
            case PARTNER -> BigDecimal.valueOf(50_000);
            case SENIOR -> BigDecimal.valueOf(1_000_000);
        };
    }
}