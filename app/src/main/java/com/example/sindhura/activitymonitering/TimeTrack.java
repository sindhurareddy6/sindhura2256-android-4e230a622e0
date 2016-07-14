package com.example.sindhura.activitymonitering;

import com.orm.SugarRecord;

/**
 * Created by Acer on 13-07-2016.
 */
public class TimeTrack extends SugarRecord {
    public String task;
    public String cat;
    public Long startTime;
    public Long endTime;
    public String status;
    public Long totalTime;
    public TimeTrack()
    {

    }
    public TimeTrack(String task,String cat,Long startTime,Long endTime,String status,Long totalTime){
        this.task=task;
        this.cat=cat;
        this.startTime=startTime;
        this.endTime=endTime;
        this.status=status;
        this.totalTime=totalTime;
    }
    public String toString(){
        return task+"   "+cat+"  "+startTime+"  "+status;
    }
}
