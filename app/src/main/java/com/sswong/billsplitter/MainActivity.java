package com.sswong.billsplitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private static ArrayList<Person> people;
    private TextView names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.names);
        names = (TextView)findViewById(R.id.people);

    }

    public void addPerson(View v){
        Person p = new Person(name.getText().toString());
        people.add(p);
        String allNames = "";
        for(Person person:people){
            allNames += person + "\n";
        }
        names.setText(allNames);
    }

    public void finish(View v){
        Intent intent = new Intent(MainActivity.this, AddItems.class);
        startActivity(intent);
    }

}
