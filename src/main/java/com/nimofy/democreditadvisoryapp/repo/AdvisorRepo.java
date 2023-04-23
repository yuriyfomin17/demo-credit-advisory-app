package com.nimofy.democreditadvisoryapp.repo;

import com.nimofy.democreditadvisoryapp.model.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorRepo extends JpaRepository<Advisor, Long> {
}
