package com.example.sindhura.activitymonitering;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.jar.Attributes;

public class Main3Activity extends AppCompatActivity {
    private Spinner spinner;
    private static Button button_four;
    private TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
        String[] items = new String[]{"Project", "Presentation", "Exercise", "Lecture"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        textView5 = (TextView) findViewById(R.id.textView5);
               OnClickButtonListnerfour();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }
    public boolean Starttimer(String taskId,String categoryId)
    {
        Spinner spinner1= (Spinner) findViewById(R.id.spinner1);
        spinner1.getSelectedItem();
        Global global = new Global();
        global.cat = spinner1.getSelectedItem().toString();
        Calendar c = Calendar.getInstance();
        Long seconds = c.getTimeInMillis();
        String catptask =  global.task +global.cat;

        catptask += seconds;
        Toast.makeText(Main3Activity.this,catptask,Toast.LENGTH_SHORT).show();


        String task = global.task;
        String cat =  global.cat;
        Long startTime = seconds;
        Long endTime = Long.getLong("0");
        String status = "started";
        TimeTracking.deleteAll(TimeTracking.class);
        TimeTracking a=new TimeTracking(task,cat,startTime,endTime,status);
        a.save();

       showdata();













        return  true;
    }
    public void showdata(){

        List<TimeTracking> notes= TimeTracking.listAll(TimeTracking.class);
        Toast.makeText(Main3Activity.this,notes.toString(),Toast.LENGTH_SHORT).show();

    }
    public void update() {

        TimeTracking xyz = TimeTracking.findById(TimeTracking.class, 3);
        xyz.task = "updated task here"; // modify the values
        xyz.save();
    }



    public void OnClickButtonListnerfour() {


        button_four = (Button) findViewById(R.id.button7);
        button_four.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.sindhura.activitymonitering.Main4Activity");
                        Starttimer("_asdf", "asdf");



                      startActivity(intent);

                    }
                }
        );


    }
   
}



