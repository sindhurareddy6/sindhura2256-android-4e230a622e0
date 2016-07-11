package com.example.sindhura.activitymonitering;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    EditText editText_name, editText_number;
    EditText editText_date, editText_date1;
    TextView textView5;
    Button button5;
    List<AddCourse> addCourses;
    ArrayAdapter<AddCourse> adapter;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText_name = (EditText) findViewById(R.id.editText_name);

        editText_number = (EditText) findViewById(R.id.editText_number);

        editText_date = (EditText) findViewById(R.id.editText_date);

        editText_date1 = (EditText) findViewById(R.id.editText_date1);

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        addCourses = (List<AddCourse>) AddCourse.listAll(AddCourse.class);
        adapter = new ArrayAdapter<AddCourse>(this, R.layout.simple_list_item, R.id.tV5);
        adapter.addAll(addCourses);



    }
   /*
    }*/
    @Override
    public void onClick(View v) {
        String Name = editText_name.getText().toString();
        String W_hours = editText_number.getText().toString();
        String S_date = editText_date.getText().toString();
        String E_date = editText_date1.getText().toString();
        switch (v.getId()) {
            case R.id.button5:
                AddCourse x = new AddCourse(Name, W_hours, S_date, E_date);
                x.save();
                adapter.add(x);
                Intent intent = new Intent("com.example.sindhura.activitymonitering.MainActivity");
                startActivity(intent);
                Toast toast= Toast.makeText(Main2Activity.this,"course       "+Name+"    has been added",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                break;


        }
    }
}





