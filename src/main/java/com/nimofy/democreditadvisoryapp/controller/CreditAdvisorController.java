package com.nimofy.democreditadvisoryapp.controller;

import com.nimofy.democreditadvisoryapp.dto.ApplicationResponse;
import com.nimofy.democreditadvisoryapp.dto.ControllerResponse;
import com.nimofy.democreditadvisoryapp.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/credit")
@RequiredArgsConstructor
public class CreditAdvisorController {
    private final ApplicationService applicationService;

    @PutMapping("/assign/{advisorId}")
    public ResponseEntity<?> assignApplication(@PathVariable Long advisorId) {
        ApplicationResponse applicationResponse = applicationService.assignApplication(advisorId);
        var response = new ControllerResponse(applicationResponse.application(), applicationResponse.message());
        return new ResponseEntity<>(response, applicationResponse.httpStatus());
    }


}
