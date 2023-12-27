//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    public PaymentServiceImpl() {
    }

    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        Reservation reservation = (Reservation)this.reservationRepository2.findById(reservationId).get();
        if (reservation == null) {
            return null;
        } else {
            int currbill = reservation.getNumberOfHours() * reservation.getSpot().getPricePerHour();
            if (currbill > amountSent) {
                throw new Exception("Insufficient Amount");
            } else {
                mode = mode.toUpperCase();
                Payment payment = reservation.getPayment();
                if (mode.equals("CASH")) {
                    payment.setPaymentMode(PaymentMode.CASH);
                } else if (mode.equals("CARD")) {
                    payment.setPaymentMode(PaymentMode.CARD);
                } else {
                    if (!mode.equals("UPI")) {
                        throw new Exception("Insufficient Amount");
                    }

                    payment.setPaymentMode(PaymentMode.UPI);
                }

                payment.setPaymentCompleted(true);
                payment.setReservation(reservation);
                reservation.getSpot().setOccupied(false);
                reservation.setPayment(payment);
                this.reservationRepository2.save(reservation);
                return payment;
            }
        }
    }
}
