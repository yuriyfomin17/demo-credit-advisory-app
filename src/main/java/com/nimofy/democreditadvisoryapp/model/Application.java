package com.nimofy.democreditadvisoryapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

enum Status {
    NEW, ASSIGNED, ON_HOLD, APPROVED, CANCELED, DECLINED
}

@Getter
@Setter
@ToString(exclude = {"applicant", "advisor"})
@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, name = "amount_of_money_usd")
    private BigDecimal amountOfMoneyUSD;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.NEW;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "advisor_id")
    @JsonIgnore
    private Advisor advisor;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "applicant_id")
    @JsonIgnore
    private Applicant applicant;
    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;
    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public void assignToAdvisor(Advisor advisor) {
        this.assignedAt = LocalDateTime.now();
        advisor.addApplication(this);
        this.setStatus(Status.ASSIGNED);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application application)) return false;
        return id != null && id.equals(application.getId());
    }
}
