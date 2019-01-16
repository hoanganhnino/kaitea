package com.example.hoang.kaitea.data;

/**
 * Created by HOANG on 8/29/2018.
 */

public class Timer
{
    private String teaName;
    private long teaTime;
    private boolean teaOn;

    public Timer(String teaName, long teaTime, boolean teaOn)
    {
        this.teaName = teaName;
        this.teaTime = teaTime;
        this.teaOn = teaOn;
    }

    public String getTeaName()
    {
        return teaName;
    }

    public void setTeaName(String teaName)
    {
        this.teaName = teaName;
    }

    public long getTeaTime()
    {
        return teaTime;
    }

    public void setTeaTime(long teaTime)
    {
        this.teaTime = teaTime;
    }

    public boolean isTeaOn()
    {
        return teaOn;
    }

    public void setTeaOn(boolean teaOn)
    {
        this.teaOn = teaOn;
    }
}
