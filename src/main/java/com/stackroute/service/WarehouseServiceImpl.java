package com.stackroute.service;

import com.stackroute.exceptions.WarehouseAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.model.Warehouse;
import com.stackroute.repository.WareHouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService
{
    WareHouseRepository warehouseRepository;

    public WarehouseServiceImpl(WareHouseRepository warehouseRepository){
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return  warehouseRepository.findAll();
    }

    @Override
    public Warehouse saveWareHouse(Warehouse wareHouse) throws WarehouseAlreadyExistsException {

        if(warehouseRepository.existsById(wareHouse.getUuid())) {
            throw new WarehouseAlreadyExistsException("warehouse already exists with id:[" + wareHouse.getUuid() + "]");
        }
        Warehouse savedWarehouse = warehouseRepository.save(wareHouse);
        return savedWarehouse;
    }

    @Override
    public boolean deleteWarehouse(String uuid) throws UserNotFoundException {
        boolean status = false;
        if(warehouseRepository.existsById(uuid)){
            warehouseRepository.deleteById(uuid);
            status=true;
            return status;

        }
        else {
            throw new UserNotFoundException("userId not exists");
        }

    }

    @Override
    public Warehouse updateUser(Warehouse wareHouse) throws UserNotFoundException{
        if(warehouseRepository.existsById(wareHouse.getUuid()))
        {
            Warehouse savedWarehouse = warehouseRepository.save(wareHouse);
            return savedWarehouse;
        }
        else{
            throw new UserNotFoundException("wareHouse not found");
        }

    }

}
