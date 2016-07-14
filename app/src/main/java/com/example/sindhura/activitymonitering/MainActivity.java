package com.example.sindhura.activitymonitering;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.jar.Attributes;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static Button button_sbm;
    List<AddCourse> addCourses;
    ArrayAdapter<AddCourse> adapter;
    ListView courseLv;
    Dialog dialog;
    TextView tV5,textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_sbm = (Button) findViewById(R.id.button);
        button_sbm.setOnClickListener(this);
       // Button button3=(Button)findViewById(R.id.button3);
       // button3.setOnClickListener(this);
        courseLv = (ListView) findViewById(R.id.courseLv);
        addCourses = (List<AddCourse>) AddCourse.listAll(AddCourse.class);
        adapter = new ArrayAdapter<AddCourse>(this, R.layout.simple_list_item, R.id.tV5);
        courseLv.setAdapter(adapter);
        courseLv.toString();
        adapter.addAll(addCourses);
        courseLv.setOnItemClickListener(new Itemlist());
    }
        class Itemlist implements AdapterView.OnItemClickListener{
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                ViewGroup viewGroup=(ViewGroup) view;
                TextView tV5=(TextView)viewGroup.findViewById(R.id.tV5);

                Global global = new Global();
                global.task = tV5.getText().toString();
                Toast.makeText(MainActivity.this,tV5.getText().toString(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent("com.example.sindhura.activitymonitering.Main3Activity");
                startActivity(intent);
             //   textView5=(TextView)findViewById(R.id.textView5);
            }
        }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                Intent intent = new Intent("com.example.sindhura.activitymonitering.Main2Activity");
                startActivity(intent);
                break;
           /* case R.id.button3:
                AddCourse.deleteAll(AddCourse.class);
                adapter.clear();
                break;*/
        }


    }
}
