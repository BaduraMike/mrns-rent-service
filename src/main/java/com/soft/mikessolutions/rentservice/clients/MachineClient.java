package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.Machine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "machine-service")
public interface MachineClient {
    @GetMapping(value = "/machines")
    Resources<Resource<Machine>> getMachines();

    @GetMapping(value = "/machines/{id}")
    Resource<Machine> getMachineById(@PathVariable("id") Long id);

    @GetMapping(value = "/machines/serialNumber/{serialNumber}")
    Resource<Machine> getMachineBySerialNumber(@PathVariable("serialNumber") String serialNumber);
}
