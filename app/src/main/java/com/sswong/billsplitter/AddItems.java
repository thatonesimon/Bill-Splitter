package com.sswong.billsplitter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Simon on 12/25/2016.
 */
public class AddItems extends AppCompatActivity {

    private EditText name;
    private EditText price;
    private TextView itemList;

    private ArrayList<Person> people;
    private ArrayList<Item> items;

    private final static String TAG = "BillSplitter";

    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "Attemping to create new activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items);

        name = (EditText) findViewById(R.id.itemName);
        price = (EditText) findViewById(R.id.itemPrice);
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
        itemList.setText(itemList.getText()+"\n"+newItemName);

        Log.d(TAG, "Item added to the list");
        name.setText("");
        price.setText("");
    }

}
