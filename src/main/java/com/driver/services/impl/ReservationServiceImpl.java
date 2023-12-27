//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Reservation;
import com.driver.model.Spot;
import com.driver.model.User;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ReservationServiceImpl() {
    }

    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        Optional<ParkingLot> optionalParkingLot = this.parkingLotRepository3.findById(parkingLotId);
        if (optionalParkingLot.isEmpty()) {
            throw new Exception("Cannot make reservation");
        } else {
            ParkingLot parkingLot = (ParkingLot)optionalParkingLot.get();
            User user = (User)this.userRepository3.findById(userId).get();
            if (user == null) {
                throw new Exception("Cannot make reservation");
            } else {
                List<Spot> spotList = parkingLot.getSpotList();
                Spot requiredSpot = null;
                int minimumPrice = Integer.MAX_VALUE;
                Iterator var11 = spotList.iterator();

                while(true) {
                    while(true) {
                        Spot spot;
                        do {
                            if (!var11.hasNext()) {
                                if (requiredSpot == null) {
                                    throw new Exception("Cannot make reservation");
                                }

                                Reservation reservation = new Reservation();
                                reservation.setNumberOfHours(timeInHours);
                                reservation.setUser(user);
                                reservation.setSpot(requiredSpot);
                                List<Reservation> reservations = user.getReservationList();
                                List<Reservation> reservationList = requiredSpot.getReservationList();
                                reservations.add(reservation);
                                reservationList.add(reservation);
                                user.setReservationList(reservations);
                                requiredSpot.setReservationList(reservationList);
                                requiredSpot.setOccupied(true);
                                this.userRepository3.save(user);
                                this.spotRepository3.save(requiredSpot);
                                return reservation;
                            }

                            spot = (Spot)var11.next();
                        } while(spot.getOccupied());

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
            }
        }
    }
}
