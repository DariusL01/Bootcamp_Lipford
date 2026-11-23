package org.example;

import java.math.BigDecimal;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicle;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract BigDecimal getTotalPrice();
    public abstract BigDecimal getMonthlyPayment();

    //Loan formula used by sales and leases
    protected BigDecimal calculateLoanPayment(BigDecimal amount, double yearlyRate, int months) {
        double monthlyRate = yearlyRate / 12;
        double loanAmount = amount.doubleValue();
        double growth = Math.pow(1 + monthlyRate, months);

        double payment = loanAmount * monthlyRate * growth / (growth -1);
        return BigDecimal.valueOf(payment);
    }
}


