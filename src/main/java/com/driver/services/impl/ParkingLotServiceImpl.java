//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;

    public ParkingLotServiceImpl() {
    }

    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
        parkingLot.setSpotList(new ArrayList());
        this.parkingLotRepository1.save(parkingLot);
        return parkingLot;
    }

    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        ParkingLot parkingLot = (ParkingLot)this.parkingLotRepository1.findById(parkingLotId).get();
        List<Spot> spots = parkingLot.getSpotList();
        Spot spot = new Spot();
        SpotType spotType = SpotType.OTHERS;
        if (numberOfWheels <= 2) {
            spotType = SpotType.TWO_WHEELER;
        } else if (numberOfWheels != 3 && numberOfWheels != 4) {
            spotType = SpotType.OTHERS;
        } else {
            spotType = SpotType.FOUR_WHEELER;
        }

        spot.setSpotType(spotType);
        spot.setPricePerHour(pricePerHour);
        spot.setReservationList(new ArrayList());
        spot.setOccupied(false);
        spot.setParkingLot(parkingLot);
        spots.add(spot);
        parkingLot.setSpotList(spots);
        this.parkingLotRepository1.save(parkingLot);
        return spot;
    }

    public void deleteSpot(int spotId) {
        this.spotRepository1.deleteById(spotId);
    }

    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        ParkingLot parkingLot = (ParkingLot)this.parkingLotRepository1.findById(parkingLotId).get();
        List<Spot> spotList = parkingLot.getSpotList();
        Spot s = null;
        Iterator var7 = spotList.iterator();

        while(var7.hasNext()) {
            Spot spot = (Spot)var7.next();
            if (spot.getId() == spotId) {
                spot.setPricePerHour(pricePerHour);
                s = (Spot)this.spotRepository1.save(spot);
            }
        }

        return s;
    }

    public void deleteParkingLot(int parkingLotId) {
        this.parkingLotRepository1.deleteById(parkingLotId);
    }
}
