package com.example.sindhura.activitymonitering;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class Main7Activity extends AppCompatActivity implements View.OnClickListener {
    TextView textView5,textView6,textView7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        GraphView graph;
        graph = (GraphView) findViewById(R.id.bargraph1);


        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(1,getTime()),
                new DataPoint(2,6),
                new DataPoint(3, 0),
                new DataPoint(4, 0),


        });

        getTime();

        graph.addSeries(series);

// styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });

        series.setSpacing(50);

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);


    }

    @Override
    public void onClick(View v) {


    }


    public Long getTime()
    {


        Global global = new Global();
        Calendar c = Calendar.getInstance();
        Long seconds = c.getTimeInMillis();
        String catptask =  global.task +global.cat;

        catptask += seconds;
        Toast.makeText(Main7Activity.this,catptask,Toast.LENGTH_SHORT).show();


        String task = global.task;
        String cat =  global.cat;
        Long W_hours = seconds;


        Long totalTime=0L;

        if(isExist(task))
        {
            Toast.makeText(Main7Activity.this,"This Course Already Exist",Toast.LENGTH_SHORT).show();

            try{
                AddCourse t = AddCourse.find(AddCourse.class,"Name = ? ", global.task).get(0);

                return t.W_hours.longValue();
            }
            catch (Exception ex){
                Toast.makeText(Main7Activity.this,"this course does'nt exist",Toast.LENGTH_SHORT).show();

            }
            //Toast.makeText(Main3Activity.this,t.toString(),Toast.LENGTH_SHORT).show();





            return 0L;
        }
               return  0L;
    }

    public boolean isExist(String task){


        List<AddCourse> t=  AddCourse.find(AddCourse.class,"Name = ? ", task);
        boolean a=(t.size() > 0);
        Toast.makeText(Main7Activity.this,String.valueOf(a),Toast.LENGTH_SHORT).show();

        return a;
    }
   /* public Long getWorkingTime() {
        Main3Activity main3Activity = new Main3Activity();
        return main3Activity.findTotalTime();

    }*/


}
