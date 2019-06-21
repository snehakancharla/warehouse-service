package com.stackroute.controller;

import com.stackroute.exceptions.WarehouseAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.model.Warehouse;
import com.stackroute.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class UserController
{
    WarehouseService warehouseService;
    @Autowired
    public UserController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/warehouse")
    public ResponseEntity<?> addWareHouse(@RequestBody Warehouse wareHouse)  throws WarehouseAlreadyExistsException {
        ResponseEntity responseEntity;
        try{
            warehouseService.saveWareHouse(wareHouse);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        }catch (WarehouseAlreadyExistsException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);

        }
        return responseEntity;
    }
    @GetMapping("/warehouses")
    public ResponseEntity<?> getAllWarehouses(){
        return  new ResponseEntity<List<Warehouse>>(warehouseService.getAllWarehouses(),HttpStatus.OK);
    }

    @PatchMapping("/warehouse")
    public ResponseEntity<?> updateAlbum(@RequestBody Warehouse wareHouse) throws UserNotFoundException {
        ResponseEntity responseEntity;
        try{
            warehouseService.updateUser(wareHouse);
            responseEntity=new ResponseEntity<String>("Updated Successfully", HttpStatus.CREATED);
        }
        catch(UserNotFoundException exception){
            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("/warehouseno/{uuid}")
    public ResponseEntity<?> deleteTrack(@PathVariable("uuid") String uuid) throws UserNotFoundException
    {
        ResponseEntity responseEntity;
        try {
            warehouseService.deleteWarehouse(uuid);
            responseEntity = new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
        }
        catch(UserNotFoundException exception){

            responseEntity=new ResponseEntity<String>(exception.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
