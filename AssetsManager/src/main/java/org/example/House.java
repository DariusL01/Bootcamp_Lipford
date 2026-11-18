package org.example;

import java.math.BigDecimal;

public class House extends Asset{
    private String address;
    private int condition; //1=excellent, 2=good, 3=fair, 4=poor
    private int squareFoot;
    private int lotSize;

    public House(String description, String dateAcquired, BigDecimal originalCost, String address, int condition, int squareFoot, int lotSize) {
        super(description, dateAcquired, originalCost);
        this.address = address;
        this.condition = condition;
        this.squareFoot = squareFoot;
        this.lotSize = lotSize;

    }

    public BigDecimal getvalue(){
        double houseValue = 0;

        if(condition == 1)
        {
            houseValue = (squareFoot * 180.00) + (lotSize * .25);
        }
        else if(condition == 2)
        {
            houseValue = (squareFoot * 130.00) + (lotSize * .25);
        }
        else if(condition == 3)
        {
            houseValue = (squareFoot * 90.00) + (lotSize * .25);
        }
        else if(condition == 4)
        {
            houseValue = (squareFoot * 80.00) + (lotSize * .25);
        }

        return BigDecimal.valueOf(houseValue);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getSquareFoot() {
        return squareFoot;
    }

    public void setSquareFoot(int squareFoot) {
        this.squareFoot = squareFoot;
    }

    public int getLotSize() {
        return lotSize;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }

}
