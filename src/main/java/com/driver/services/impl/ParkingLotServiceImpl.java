package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot=new ParkingLot(name,address);
        parkingLotRepository1.save(parkingLot);
        return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour){
        Spot Spot=new Spot();
        Spot.setPricePerHour(pricePerHour);
        if(numberOfWheels>4){
            Spot.setSpotType(SpotType.OTHERS);
        }
        else if(numberOfWheels>2){
            Spot.setSpotType(SpotType.FOUR_WHEELER);
        }
        else Spot.setSpotType(SpotType.TWO_WHEELER);

        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
        Spot.setParkingLot(parkingLot);

        //bidirectional
        parkingLot.getSpotList().add(Spot);

        parkingLotRepository1.save(parkingLot);


        return Spot;
    }

    @Override
    public void deleteSpot(int spotId){

        spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour){

        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();



        Spot Spot=null;
        List<Spot> spotList=parkingLot.getSpotList();
        for(Spot spot1:spotList){
            if(spot1.getId()==spotId)
                Spot=spot1;
        }




        //Spot spot = spotRepository1.findById(spotId).get();


        Spot.setParkingLot(parkingLot);
        Spot.setPricePerHour(pricePerHour);

        // parkingLotRepository1.save(parkingLot);

        spotRepository1.save(Spot);

        return Spot;



    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}