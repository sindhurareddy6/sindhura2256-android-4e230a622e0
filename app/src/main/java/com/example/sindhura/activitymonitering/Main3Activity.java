package com.example.sindhura.activitymonitering;

import android.content.Intent;
import android.graphics.Color;
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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;

public class Main3Activity extends AppCompatActivity {
    private Spinner spinner;
    private static Button button_four,button_five,button_six,button_seven;
    private TextView textView5,textView6,textView7;
    public Long diff;

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


               OnClickButtonListnerfour();
//       OnClickButtonListnerfive();
        OnClickButtonListnersix();
        OnClickButtonListnerseven();

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
        //TimeTracking.deleteAll(TimeTracking.class);


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
        String status = "start";

        Long totalTime=0L;

        if(isExist(task, cat))
        {
            Toast.makeText(Main3Activity.this,"This Course Already Exist",Toast.LENGTH_SHORT).show();

            try{
                TimeTrack t = TimeTrack.find(TimeTrack.class,"task = ? and cat = ?", global.task,global.cat).get(0);
                Toast.makeText(Main3Activity.this, "status : " + t.status.equals("pause"), Toast.LENGTH_SHORT).show();
                if(t.status.equals("pause")) {
                    Calendar c2 = Calendar.getInstance();
                    Long seconds2 = c2.getTimeInMillis();
                    t.startTime = seconds2;
                    t.status = "start";
                    t.save();
                    Toast.makeText(Main3Activity.this, getDurationBreakdown(t.startTime), Toast.LENGTH_SHORT).show();
                    // TextView textView6= (TextView)findViewById(R.id.textView6);
                    //textView6.setText("workdone now"+getDurationBreakdown(diff));
                }
            }
            catch (Exception ex){
                Toast.makeText(Main3Activity.this,"this course does'nt exist",Toast.LENGTH_SHORT).show();

            }
            //Toast.makeText(Main3Activity.this,t.toString(),Toast.LENGTH_SHORT).show();





            return false;
        }
        TimeTrack a=new TimeTrack(task,cat,startTime,endTime,status,totalTime);
        a.save();





        return  true;
    }
    public boolean Pause()
    {
        //TimeTracking.deleteAll(TimeTracking.class);


        Spinner spinner1= (Spinner) findViewById(R.id.spinner1);
        spinner1.getSelectedItem();
        Global global = new Global();
        global.cat = spinner1.getSelectedItem().toString();
        Calendar c = Calendar.getInstance();
        Long seconds = c.getTimeInMillis();
        String catptask =  global.task +global.cat;

        catptask += seconds;
        Toast.makeText(Main3Activity.this,"pause fun",Toast.LENGTH_SHORT).show();


        String task = global.task;
        String cat =  global.cat;
        Long startTime = seconds;
        Long endTime = Long.getLong("0");
        String status = "start";
        Long totalTime=0L;

        if(isExist(task, cat))
        {
            Toast.makeText(Main3Activity.this,"This Course Already Exist",Toast.LENGTH_SHORT).show();

            TimeTrack t;
            try{
                t=  TimeTrack.find(TimeTrack.class,"task = ? and cat = ?", global.task,global.cat).get(0);

                if(t.status.equals("start")) {
                    Calendar c1 = Calendar.getInstance();
                    Long seconds1 = c1.getTimeInMillis();
                    Long startTime1 = t.startTime.longValue();
                    Long diff = seconds1 - startTime1;
                    t.totalTime += diff;
                    t.status = "pause";
                    t.save();
                    Toast.makeText(Main3Activity.this, getDurationBreakdown(t.totalTime), Toast.LENGTH_SHORT).show();
                     TextView textView5= (TextView)findViewById(R.id.textView5);
                    textView5.setText("workdone now  "+getDurationBreakdown(diff));
                    TextView textView7=(TextView)findViewById(R.id.textView7);
                    textView7.setText("TotalWorkDone"+getDurationBreakdown(t.totalTime));
                }
            }
            catch (Exception ex){
                Toast.makeText(Main3Activity.this,"this course does'nt exist",Toast.LENGTH_SHORT).show();

            }

            return false;
        }


        return  true;
    }
    public void showdata(){

        List<TimeTrack> notes= TimeTrack.listAll(TimeTrack.class);
        Toast.makeText(Main3Activity.this,notes.toString(),Toast.LENGTH_SHORT).show();
        //getValue(notes.get(0).task,notes.get(0).cat);

    }
   /* public void update() {

        TimeTrack t=  TimeTrack.find(TimeTrack.class,"task = ? and cat = ?", task,cat).get(0);
        Toast.makeText(Main3Activity.this,t.toString(),Toast.LENGTH_SHORT).show();
        t.task = "updated task here"; // modify the values
        t.save();
    }*/
    public boolean isExist(String task, String cat){


        List<TimeTrack> t=  TimeTrack.find(TimeTrack.class,"task = ? and cat = ?", task,cat);
        boolean a=(t.size() > 0);
        Toast.makeText(Main3Activity.this,String.valueOf(a),Toast.LENGTH_SHORT).show();

        return a;
    }
    /* public void EndTask(){

        Global global=new Global();
        TimeTrack t;
        try{
         t=  TimeTrack.find(TimeTrack.class,"task = ? and cat = ?", global.task,global.cat).get(0);
        }
        catch (Exception ex){
            Toast.makeText(Main3Activity.this,"this course does'nt exist",Toast.LENGTH_SHORT).show();
            return;
        }
        //Toast.makeText(Main3Activity.this,t.toString(),Toast.LENGTH_SHORT).show();
        if(t.status.equals("start")) {
            Calendar c = Calendar.getInstance();
            Long seconds = c.getTimeInMillis();
            Long startTime = t.startTime.longValue();
            Long diff = seconds - startTime;
            t.totalTime += seconds;
            t.endTime = seconds;
            t.status = "finish";
            t.save();
            Toast.makeText(Main3Activity.this, getDurationBreakdown(t.totalTime), Toast.LENGTH_SHORT).show();
           // TextView textView6= (TextView)findViewById(R.id.textView6);
            //textView6.setText("workdone now"+getDurationBreakdown(diff));
        }

        Toast.makeText(Main3Activity.this, "Alread Finished", Toast.LENGTH_SHORT).show();

    }*/

    public Long findTotalTime() {
        Global global = new Global();
        List<TimeTrack> t = TimeTrack.find(TimeTrack.class, "task = ?", global.task);
        Long totTime= 0L;

        Long seconds;
        for (int i = 0; i < t.size(); i++) {
            TimeTrack tr = t.get(i);

            Toast.makeText(Main3Activity.this, tr.toString(), Toast.LENGTH_SHORT).show();
            if( tr.startTime==null)continue;
            Long startTime = tr.startTime.longValue();

            Long diff;


            if (!tr.status.equals("finish")) {
                Calendar c = Calendar.getInstance();
                 seconds = c.getTimeInMillis();
                diff = seconds - startTime;

                 }
            else{
                 seconds = tr.endTime.longValue();
                diff = 0L;
            }

            totTime = (diff + tr.totalTime.longValue());
            Toast.makeText(Main3Activity.this, totTime.toString(), Toast.LENGTH_LONG).show();
            totTime+=diff;

        }


        Toast.makeText(Main3Activity.this, "total time"+getDurationBreakdown(totTime), Toast.LENGTH_SHORT).show();
        //TextView textView5=(TextView)findViewById(R.id.textView5);
       // textView5.setText("TotalWorkingHoursDone="+findTotalTime());

        return totTime;
    }
    public String getDurationBreakdown(long millis)
    {
        if(millis < 0)
        {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        sb.append(days);
        sb.append(" Days ");
        sb.append(hours);
        sb.append(" Hours ");
        sb.append(minutes);
        sb.append(" Minutes ");
        sb.append(seconds);
        sb.append(" Seconds");

        return(sb.toString());
    }



    public void OnClickButtonListnerfour() {


        button_four = (Button) findViewById(R.id.button7);
        button_four.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Intent intent = new Intent("com.example.sindhura.activitymonitering.Main4Activity");
                        Starttimer("_asdf", "asdf");



                     // startActivity(intent);

                    }
                }
        );


    }
/*   public void OnClickButtonListnerfive() {


        button_five = (Button) findViewById(R.id.button_4);
        button_five.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // Intent intent = new Intent("com.example.sindhura.activitymonitering.Main4Activity");
                        EndTask();



                       // startActivity(intent);

                    }
                }
        );


    }*/
    public void OnClickButtonListnersix() {


        button_six = (Button) findViewById(R.id.button_Details);
        button_six.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent("com.example.sindhura.activitymonitering.Main7Activity");
                        startActivity(intent);

                    }
                }
        );


    }
    public void OnClickButtonListnerseven() {


        button_seven = (Button) findViewById(R.id.button2);
        button_seven.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(Main3Activity.this, "pause clicked", Toast.LENGTH_SHORT).show();
                        Pause();


                        //Intent intent = new Intent("com.example.sindhura.activitymonitering.Main7Activity");
                        //startActivity(intent);

                    }
                }
        );


    }


   
}



