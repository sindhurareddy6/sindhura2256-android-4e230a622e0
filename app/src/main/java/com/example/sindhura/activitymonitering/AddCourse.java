package com.example.sindhura.activitymonitering;

import android.widget.Adapter;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Acer on 26-06-2016.
 */
public class AddCourse extends SugarRecord {
    private String Name;
    private String W_hours;
    private String S_date;
    private String E_date;

    public AddCourse(){

    }

    public  AddCourse(String Name, String W_hours, String S_date, String E_date){
        this.Name = Name;

        this.W_hours = W_hours;
        this.S_date= S_date;
        this.E_date=E_date;
    }

    public String toString() {

        return  Name  ;
    }
}

