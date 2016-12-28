package com.sswong.billsplitter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

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

        // make array of switches, one for each person
        switches = new ArrayList<>(names.size());
        for(int i = 0; i < switches.size(); i++){
            ToggleButton newButton = new ToggleButton(this);
            newButton.setText(names.get(i));
            newButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            newButton.setId(i);
            switches.add(newButton);
            switchHolder.addView(newButton);
            Log.d(TAG, "Button for "+names.get(i)+" created");
        }
        Log.d(TAG, "Activity successfully created");

    }

    public void addItem(View v){
        String newItemName = name.getText().toString().trim();
        if(newItemName.equals("")){
            Toast.makeText(this, "Enter a valid item name", Toast.LENGTH_SHORT).show();
            return;
        }
        float newItemPrice = Float.valueOf(price.getText().toString());
        if(newItemPrice <= 0){
            Toast.makeText(this, "Enter a valid price", Toast.LENGTH_SHORT).show();
            return;
        }
        Item i = new Item(newItemName,newItemPrice,people);
        items.add(i);

        // update itemList
        if(items.size()==1){
            itemList.setText(newItemName);
        }
        else {
            itemList.setText(itemList.getText() + "\n" + newItemName);
        }
        Log.d(TAG, "Item added to the list");
        name.setText("");
        price.setText("");
    }

}
