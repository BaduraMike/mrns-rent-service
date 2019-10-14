package com.soft.mikessolutions.rentservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {
    private Long identityNumber;
    private String companyName;

    public Company() {
    }

    public Long getIdentityNumber() {
        return identityNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Company{" +
                "identityNumber=" + identityNumber +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
