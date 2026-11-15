package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    List<Vehicle> inventoryList = new ArrayList<>();

    public Dealership(String name, String address, String phone, List<Vehicle> inventoryList) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventoryList = inventoryList;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<Vehicle> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Vehicle> inventoryList) {
        this.inventoryList = inventoryList;
    }

}
