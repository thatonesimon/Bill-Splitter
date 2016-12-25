package com.sswong.billsplitter;

import java.util.ArrayList;

/**
 * Created by Simon on 12/25/2016.
 */

public class Item {
    private String name;
    private double price;
    private ArrayList<Person> whoSplit;
    private int numSplit;
    private double pricePerPerson;

    Item(String name, double price, ArrayList<Person> whoSplit){
        this.name = name;
        this.price = price;
        this.whoSplit = whoSplit;
        this.numSplit = whoSplit.size();
        this.pricePerPerson = price/numSplit;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public ArrayList<Person> whoSplit(){
        return whoSplit;
    }

    public int numSplit(){
        return numSplit;
    }

    public double pricePerPerson(){
        return pricePerPerson;
    }
}
