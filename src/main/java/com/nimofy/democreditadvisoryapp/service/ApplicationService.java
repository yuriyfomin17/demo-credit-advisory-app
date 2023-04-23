package com.nimofy.democreditadvisoryapp.service;

import com.nimofy.democreditadvisoryapp.dto.ApplicationResponse;
import com.nimofy.democreditadvisoryapp.model.Advisor;
import com.nimofy.democreditadvisoryapp.model.Application;
import com.nimofy.democreditadvisoryapp.repo.AdvisorRepo;
import com.nimofy.democreditadvisoryapp.repo.ApplicationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final AdvisorRepo advisorRepo;
    private final ApplicationRepo applicationRepo;

    @Transactional
    public ApplicationResponse assignApplication(Long advisorId) {
        Optional<Advisor> optionalAdvisor = advisorRepo.findById(advisorId);
        return optionalAdvisor.map(this::handleAdvisor)
                .orElseGet(() -> new ApplicationResponse(null, String.format("Advisor with %d id is not present", advisorId), HttpStatus.BAD_REQUEST));
    }

    private ApplicationResponse handleAdvisor(Advisor advisor) {
        if (advisor.isApplicationAssigned()) {
            return new ApplicationResponse(null, String.format("Advisor with %d id has an assigned application", advisor.getId()), HttpStatus.BAD_REQUEST);
        }
        Optional<Application> oldUnAssignedApplication = applicationRepo.findOldestNewApplication(advisor.getLowerBound(), advisor.getUpperBound());

        return oldUnAssignedApplication.map(application -> handleApplication(advisor, application))
                .orElseGet(() -> new ApplicationResponse(null, "There are no un assigned applications", HttpStatus.BAD_REQUEST));
    }

    private ApplicationResponse handleApplication(Advisor advisor, Application application) {
        application.assignToAdvisor(advisor);
        return new ApplicationResponse(application, "Application is assigned to advisor", HttpStatus.OK);
    }


}
