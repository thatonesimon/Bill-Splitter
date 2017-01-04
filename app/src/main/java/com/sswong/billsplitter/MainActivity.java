package com.sswong.billsplitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private TextView names;

    private static ArrayList<String> people;

    private final static String TAG = "BillSplitter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.names);
        names = (TextView)findViewById(R.id.people);

        people = new ArrayList<>(0);

    }

    public void addPerson(View v){
        String newName = name.getText().toString().trim();
        if(newName.equals("")){
            Toast.makeText(this, "Enter a name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(people.contains(newName)){
            Toast.makeText(this, "Name is already in the list", Toast.LENGTH_SHORT).show();
            return;
        }
        people.add(newName);

        // update names
        if(people.size() == 1){
            names.setText("");
        }
        names.setText(names.getText()+newName+"\n");

        Log.d(TAG, "Name added to the list");
        name.setText("");
    }

    public void finish(View v){
        if(people.size() < 2){
            Toast.makeText(this, "You need at least 2 people to split a bill.", Toast.LENGTH_LONG).show();
            return;
        }
        Log.d(TAG, "Finished adding names, moving onto adding items");
        Intent intent = new Intent(MainActivity.this, AddItems.class);
        intent.putExtra("PEOPLE", people);
        startActivity(intent);
    }

}
