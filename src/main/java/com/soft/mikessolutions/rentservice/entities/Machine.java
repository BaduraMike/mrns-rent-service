package com.soft.mikessolutions.rentservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Machine {
    private Long identityNumber;
    private String serialNumber;
    private String udtNumber;
    private String machineType;

    public Machine() {
    }

    public Long getIdentityNumber() {
        return identityNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getUdtNumber() {
        return udtNumber;
    }

    public String getMachineType() {
        return machineType;
    }
}
