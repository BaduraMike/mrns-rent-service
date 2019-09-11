package com.soft.mikessolutions.rentservice.clients;

import com.soft.mikessolutions.rentservice.entities.Machine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "machine-service")
public interface MachineClient {
    @GetMapping("/machines")
    Resources<Resource<Machine>> getMachines();

    @GetMapping("/machines/{id}")
    Resource<Machine> getMachineById(@RequestParam("id") Long id);
}
