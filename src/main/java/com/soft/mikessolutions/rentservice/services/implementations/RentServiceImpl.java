package com.soft.mikessolutions.rentservice.services.implementations;

import com.soft.mikessolutions.rentservice.clients.MachineClient;
import com.soft.mikessolutions.rentservice.clients.UserClient;
import com.soft.mikessolutions.rentservice.entities.Rent;
import com.soft.mikessolutions.rentservice.exceptions.RentNotFoundException;
import com.soft.mikessolutions.rentservice.repositories.RentRepository;
import com.soft.mikessolutions.rentservice.services.RentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;
    private final MachineClient machineClient;
    private final UserClient userClient;

    RentServiceImpl(RentRepository rentRepository, MachineClient machineClient, UserClient userClient) {
        this.rentRepository = rentRepository;
        this.machineClient = machineClient;
        this.userClient = userClient;
    }

    @Override
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    @Override
    public Rent findById(Long id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

    @Override
    public Rent save(Rent rent) {
        return null;
    }

    @Override
    public void delete(Rent rent) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Rent addMachineToRent(Long rentId, String serialNumber) {
        Rent rent = findById(rentId);
        Long machineIdToAdd = machineClient.getMachineBySerialNumber(serialNumber).getContent().getIdentityNumber();
        rent.setMachineId(machineIdToAdd);
        rentRepository.save(rent);
        return rent;
    }

    @Override
    public Rent addUserToRent(Long rentId, String email) {
        Rent rent = findById(rentId);
        Long userIdToAdd = userClient.getUserByEmail(email).getContent().getIdentityNumber();
        rent.setUserId(userIdToAdd);
        rentRepository.save(rent);
        return rent;
    }
}
