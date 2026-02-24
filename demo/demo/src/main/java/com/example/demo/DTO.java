package com.example.demo;

import lombok.Getter;
import lombok.Setter;

public class DTO {
//    @Getter
//    @Setter
    private int id;
    private  String isactive;
    private String start_time;
    private String end_time;


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getEnd_time(){
        return end_time;
    }
    public void setEnd_time(String end_time){
        this.end_time = end_time;
    }
    public void setStart_time(String start_time){
        this.start_time = start_time;
    }
    public String getStart_time(){
        return start_time;
    }

    public void setIsactive(String isactive){
        this.isactive = isactive;
    }
    public String getIsactive(){
        return isactive;
    }




}
