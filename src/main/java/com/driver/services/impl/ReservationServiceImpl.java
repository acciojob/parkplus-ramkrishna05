package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        Optional<ParkingLot>optionalParkingLot=parkingLotRepository3.findById(parkingLotId);
        if(optionalParkingLot.isEmpty())
        {
            throw new Exception("Cannot make reservation");
        }
        ParkingLot parkingLot=optionalParkingLot.get();
        User user=userRepository3.findById(userId).get();
        if(user==null)
        {
            throw new Exception("Cannot make reservation");
        }
        //Reserve a spot in the given parkingLot such that the total price is minimum. Note that the price per hour for each spot is different
        //Note that the vehicle can only be parked in a spot having a type equal to or larger than given vehicle
        //If parkingLot is not found, user is not found, or no spot is available, throw "Cannot make reservation" exception.
        List<Spot>spotList=parkingLot.getSpotList();
        Spot requiredSpot=null;
        int minimumPrice=Integer.MAX_VALUE;

        for(Spot spot:spotList)
        {
            if(!spot.getOccupied()) {
                if (numberOfWheels <= 2 && minimumPrice > spot.getPricePerHour()) {
                    minimumPrice = spot.getPricePerHour();
                    requiredSpot = spot;

                } else if (numberOfWheels <= 4 && minimumPrice > spot.getPricePerHour()) {
                    minimumPrice = spot.getPricePerHour();
                    requiredSpot = spot;
                } else {
                    minimumPrice = spot.getPricePerHour();
                    requiredSpot = spot;
                }
            }

        }
        if(requiredSpot==null)
        {
            throw new Exception("Cannot make reservation");
        }

        Reservation reservation=new Reservation();
        reservation.setNumberOfHours(timeInHours);
        reservation.setUser(user);
        reservation.setSpot(requiredSpot);

        List<Reservation>reservations=user.getReservationList();
        List<Reservation>reservationList=requiredSpot.getReservationList();
        reservations.add(reservation);
        reservationList.add(reservation);

        user.setReservationList(reservations);
        requiredSpot.setReservationList(reservationList);

        requiredSpot.setOccupied(true);

       userRepository3.save(user);
       spotRepository3.save(requiredSpot);

       return reservation;




    }
}
