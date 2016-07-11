package com.example.sindhura.activitymonitering;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Main4Activity extends AppCompatActivity {
    private static Button button_five;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        OnClickButtonListnerfive();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void OnClickButtonListnerfive(){


        button_five = (Button) findViewById(R.id.button6);
        button_five.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.sindhura.activitymonitering.Main5Activity");
                        Endtimer("_asdf", "asdf");
                        startActivity(intent);

                    }
                }
        );
    }
    public boolean Endtimer(String taskId,String categoryId)
    {

        Global global = new Global();
        Calendar c = Calendar.getInstance();
        Long seconds = c.getTimeInMillis();
        String catptask =  global.task +global.cat;

        catptask += seconds;
        Toast.makeText(Main4Activity.this,catptask,Toast.LENGTH_SHORT).show();


        String task = global.task;
        String cat =  global.cat;
        Long startTime =  Long.getLong("0");
        Long endTime =seconds;
        String status = "ended";
        TimeTracking.deleteAll(TimeTracking.class);
        TimeTracking a=new TimeTracking(task,cat,startTime,endTime,status);
        a.save();


        return  true;
    }
}


