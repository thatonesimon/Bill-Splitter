package com.sswong.billsplitter;

import java.util.ArrayList;

/**
 * Created by Simon on 12/25/2016.
 */

public class Person {
    private String name;
    private ArrayList<Item> itemsSplit;
    private double owed;
    private int numItems;

    Person(String name){
        this.name = name;
        itemsSplit = new ArrayList<>(0);
    }

    public void addItem(Item i){
        itemsSplit.add(i);
        owed += i.getPrice()/i.numSplit();
        numItems++;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Item> itemsBought(){
        return itemsSplit;
    }

    public double moneyOwed(){
        return owed;
    }

    public int numItems(){
        return numItems;
    }
}
