package com.nimofy.democreditadvisoryapp.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    private String city;
    private String street;
    private String number;
    private String zip;
    private String apt;
}
