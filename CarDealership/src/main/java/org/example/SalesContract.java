package org.example;

import java.math.BigDecimal;

public class SalesContract extends Contract {
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;
    }

    public boolean isFinance() {return finance; }
    public void setFinance(boolean finance) { this.finance = finance; }

    public BigDecimal getSalesTaxAmount() {
        return getVehicle().getPrice().multiply(new BigDecimal("0.05"));
    }

    public BigDecimal getRecordingFee() {
        return new BigDecimal("100");
    }

    public BigDecimal getProcessingFee() {
        if (getVehicle().getPrice().doubleValue() < 10000) {
            return new BigDecimal("295");
        }
        return new BigDecimal("495");
    }

    @Override
    public BigDecimal getTotalPrice() {
        return getVehicle().getPrice().add(getSalesTaxAmount()).add(getRecordingFee()).add(getProcessingFee());
    }

    @Override
    public BigDecimal getMonthlyPayment() {
        if (!finance) {
            return BigDecimal.ZERO;
        }
        if (getVehicle().getPrice().doubleValue() >= 10000) {
            return calculateLoanPayment(getTotalPrice(), 0.0425, 48);
        }
        return calculateLoanPayment(getTotalPrice(), 0.0525, 24);
    }
}
