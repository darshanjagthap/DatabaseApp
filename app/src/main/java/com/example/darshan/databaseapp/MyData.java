package com.example.darshan.databaseapp;

/**
 * Created by Darshan on 18-12-2015.
 */

public class MyData {
   private String num,name;

    public MyData() {
        num="";
        name="";
    }

    public MyData(String num1,String name1) {
        num=num1;
        name=name1;
    }
    public void setNum(String a)
    {
        num=a;
    }
    public void setName(String b)
    {
        name=b;
    }
    public String getNum()
    {
       return num;
    }

    public String getName()
    {
        return name;
    }
}
