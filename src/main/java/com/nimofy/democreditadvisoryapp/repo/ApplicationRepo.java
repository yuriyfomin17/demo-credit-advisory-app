package com.nimofy.democreditadvisoryapp.repo;

import com.nimofy.democreditadvisoryapp.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application, Long> {

    @Query("select a from Application a where a.status = 'NEW' " +
            "and a.amountOfMoneyUSD >= :lowerBound " +
            "and a.amountOfMoneyUSD < :upperBound " +
            "order by a.id asc")
    Optional<Application> findOldestNewApplication(BigDecimal lowerBound, BigDecimal upperBound);
}
