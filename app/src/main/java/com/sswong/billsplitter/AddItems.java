package com.sswong.billsplitter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import static com.sswong.billsplitter.R.id.names;

/**
 * Created by Simon on 12/25/2016.
 */
public class AddItems extends AppCompatActivity {

    private EditText name;
    private EditText price;
    private LinearLayout switchHolder;
    private TextView itemList;

    private ArrayList<Person> people;
    private ArrayList<Item> items;
    private ArrayList<ToggleButton> switches;

    private final static String TAG = "BillSplitter";

    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "Attempting to create new activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items);

        name = (EditText) findViewById(R.id.itemName);
        price = (EditText) findViewById(R.id.itemPrice);
        switchHolder = (LinearLayout) findViewById(R.id.switchHolder);
        itemList = (TextView) findViewById(R.id.itemList);

        people = new ArrayList<>(0);
        items = new ArrayList<>(0);

        // get names from MainActivity and put into ArrayList of people
        Intent intent = getIntent();
        ArrayList<String> names = intent.getExtras().getStringArrayList("PEOPLE");
        for(String n:names){
            Person p = new Person(n);
            people.add(p);
        }
        Log.d(TAG, "Adding button for each name...");
        // make array of switches, one for each person
        switches = new ArrayList<>(names.size());
        for(int i = 0; i < names.size(); i++){
            makeSwitch(i);
        }
        Log.d(TAG, "Activity successfully created");

    }

    @Override
    public void onBackPressed(){
        Log.d(TAG, "Back button pressed");
        // startActivity(new Intent(AddItems.this, Popup.class));
    }

    public void addItem(View v){
        String newItemName = name.getText().toString().trim();
        if(newItemName.equals("")){
            Toast.makeText(this, "Enter a valid item name", Toast.LENGTH_SHORT).show();
            return;
        }
        String newPrice = price.getText().toString();
        if(newPrice.equals("")){
            Toast.makeText(this, "Enter a valid price", Toast.LENGTH_SHORT).show();
            return;
        }
        float newItemPrice = Float.valueOf(newPrice);
        if(newItemPrice <= 0 ){
            Toast.makeText(this, "Enter a valid price", Toast.LENGTH_SHORT).show();
            return;
        }

        // find out who is splitting the item
        ArrayList<Person> peopleSplitting = new ArrayList<>(0);
        for(int j = 0; j < people.size(); j++){
            if(switches.get(j).isChecked()){
                peopleSplitting.add(people.get(j));
            }
        }
        if(peopleSplitting.size() < 1){
            Toast.makeText(this, "Item has to have at least 1 person sharing",Toast.LENGTH_SHORT).show();
            return;
        }

        Item i = new Item(newItemName,newItemPrice,peopleSplitting);

        // add item to people splitting
        for(Person p:peopleSplitting){
            p.addItem(i);
        }

        items.add(i);

        // update itemList
        if(items.size() == 1){
            itemList.setText("");
        }
        itemList.setText(itemList.getText()+ newItemName+"\n");
        Log.d(TAG, "Item added to the list");

        // reset text fields and switches
        name.setText("");
        price.setText("");
        for(int j = 0; j < people.size(); j++){
            switches.get(j).setChecked(false);
        }
    }

    private void makeSwitch(final int i){
        final ToggleButton newSwitch = new ToggleButton(this);
        newSwitch.setText(people.get(i).getName());
        newSwitch.setTextOn(people.get(i).getName());
        newSwitch.setTextOff(people.get(i).getName());
        newSwitch.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        newSwitch.setId(i);
        switches.add(newSwitch);
        switchHolder.addView(newSwitch);
        Log.d(TAG, "Button for "+people.get(i).getName()+" created");
    }

    public void finished(View v){
        if(items.size() < 1){
            Toast.makeText(this, "You need at least 1 item to split a bill.", Toast.LENGTH_LONG).show();
            return;
        }
        Log.d(TAG, "Finished adding items, calculating cost per person");
        Intent intent = new Intent(AddItems.this, Receipt.class);

        ArrayList<String> names = new ArrayList<>(people.size());
        ArrayList<Double> owed = new ArrayList<>(people.size());
        for(Person p:people){
            names.add(p.getName());
            owed.add(p.moneyOwed());
        }
        intent.putExtra("NAMES", names);
        intent.putExtra("OWED", owed);
        startActivity(intent);
    }


}
