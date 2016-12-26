package com.sswong.billsplitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private static ArrayList<String> people;
    private TextView names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        people = new ArrayList<>(0);

        name = (EditText)findViewById(R.id.names);
        names = (TextView)findViewById(R.id.people);

    }

    public void addPerson(View v){
        String newName = name.getText().toString().trim();
        if(newName.equals("")){
            Toast.makeText(this, "Enter a name", Toast.LENGTH_LONG).show();
            return;
        }
        if(people.contains(newName)){
            Toast.makeText(this, "Name is already in the list", Toast.LENGTH_LONG).show();
            return;
        }
        people.add(newName);
        String allNames = "";
        for(String person:people){
            allNames += person + "\n";
        }
        names.setText(allNames);
        name.setText("");
    }

    public void finish(View v){
        Intent intent = new Intent(MainActivity.this, AddItems.class);
        startActivity(intent);
    }

}
