package com.nimofy.democreditadvisoryapp.dto;

import com.nimofy.democreditadvisoryapp.model.Application;

public record ControllerResponse(Application application, String message) {
}
