package com.soft.mikessolutions.rentservice.services;

import com.soft.mikessolutions.rentservice.entities.Rent;
import org.springframework.stereotype.Service;

@Service
public interface RentService extends CrudService<Rent,Long>{

    Rent addMachineToRent(Long rentId, String serialNumber);

    Rent addUserToRent(Long rentId, String email);
}
