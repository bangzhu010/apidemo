package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private MyRelativeLayout myRelativeLayout;
    private CheckBox checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
        myRelativeLayout = (MyRelativeLayout)findViewById(R.id.myRelativeLayout);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        myRelativeLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(checkbox.isChecked()){
            checkbox.setChecked(false);
        } else {
            checkbox.setChecked(true);
        }
    }
}
