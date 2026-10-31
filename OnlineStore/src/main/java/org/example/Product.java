package org.example;

public class Product {
    private String sKU;
    private String item;
    private double price;
    private String department;

    public Product(String sKU, String item, double price, String department) {
        this.sKU = sKU;
        this.item = item;
        this.price = price;
        this.department = department;
    }

    public String getsKU() {
        return sKU;
    }

    public void setsKU(String sKU) {
        this.sKU = sKU;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
