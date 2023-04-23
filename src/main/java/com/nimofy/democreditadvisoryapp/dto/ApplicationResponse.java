package com.nimofy.democreditadvisoryapp.dto;

import com.nimofy.democreditadvisoryapp.model.Application;
import org.springframework.http.HttpStatus;

public record ApplicationResponse(Application application, String message, HttpStatus httpStatus) {
}
