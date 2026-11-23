package org.example;

import java.math.BigDecimal;

public class LeaseContract extends Contract {

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    public BigDecimal getExpectedEndingValue() {
        return getVehicle().getPrice().multiply(new BigDecimal("0.50"));
    }

    public BigDecimal getLeaseFee() {
        return getVehicle().getPrice().multiply(new BigDecimal("0.50"));
    }

    @Override
    public BigDecimal getTotalPrice() {
        return getExpectedEndingValue().add(getLeaseFee());
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        return calculateLoanPayment(getTotalPrice(), 0.04, 36);
    }
}
