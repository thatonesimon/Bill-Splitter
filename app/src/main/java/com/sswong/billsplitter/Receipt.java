package com.sswong.billsplitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Simon on 1/3/2017.
 */

public class Receipt extends AppCompatActivity {

    private TextView names;
    private TextView owed;

    private ArrayList<String> people;
    private ArrayList<Double> amountOwed;

    private final static String TAG = "BillSplitter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt);
        Log.d(TAG, "New activity started");

        names = (TextView) findViewById(R.id.names);
        owed = (TextView) findViewById(R.id.owed);

        Intent intent = getIntent();
        Log.d(TAG, "Getting extras");
        people = intent.getExtras().getStringArrayList("NAMES");
        amountOwed = (ArrayList<Double>) intent.getExtras().getSerializable("OWED");

        for(int i = 0; i < people.size(); i++){
            names.setText(names.getText()+people.get(i)+"\n");
            owed.setText(""+owed.getText()+amountOwed.get(i)+"\n");
        }

    }
}
