package model;

import java.time.LocalTime;

public class UtilisedData {
    int id;
    LocalTime time;
    int count;

    public UtilisedData(int id){
        this.time=LocalTime.now();
        this.count=0;
    }
    public void increaseCount(){
        this.count+=1;
    }
    public LocalTime getTime(){
        return this.time;
    }

    public int getCount(){
        return this.count;
    }

    public void resetCount(){
        this.count=0;
    }

    public void setTime(LocalTime time){
        this.time=time;
    }
}
