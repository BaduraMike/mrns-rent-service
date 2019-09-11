package com.soft.mikessolutions.rentservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Machine {
    private Long id;
    private String serialNumber;
    private String udtNumber;
    private String machineType;

    public Machine() {
    }

    public Long getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getUdtNumber() {
        return udtNumber;
    }

    public void setUdtNumber(String udtNumber) {
        this.udtNumber = udtNumber;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }
}
