package com.example.sindhura.activitymonitering;

import android.widget.TextView;

import com.orm.SugarRecord;

/**
 * Created by Acer on 10-07-2016.
 */
public class TimeTracking extends SugarRecord {
    public String task;
    private String cat;
    private Long startTime;
    private Long endTime;
    private String status;
    public TimeTracking()
    {

    }
    public TimeTracking(String task,String cat,Long startTime,Long endTime,String status)
    {
        this.task=task;
        this.cat=cat;
        this.startTime= startTime;
        this.endTime=endTime;
        this.status=status;
    }
    public String toString(){
        return task+"   "+cat+"  "+startTime+"  "+status;

    }


}
