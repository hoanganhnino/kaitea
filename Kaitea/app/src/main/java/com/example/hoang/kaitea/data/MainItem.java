package com.example.hoang.kaitea.data;

/**
 * Created by HOANG on 8/27/2018.
 */

public class MainItem
{
    private String name;
    private String img;

    public MainItem(String name, String img)
    {
        this.name = name;
        this.img = img;
    }

    public String getImg()
    {
        return img;
    }

    public String getName()
    {
        return name;

    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
