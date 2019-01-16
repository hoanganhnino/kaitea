package com.example.hoang.kaitea.data;

/**
 * Created by HOANG on 9/8/2018.
 */

public class Contact
{
    private String name;
    private String sdt;

    public Contact(String name, String sdt)
    {
        this.name = name;
        this.sdt = sdt;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSdt()
    {
        return sdt;
    }

    public void setSdt(String sdt)
    {
        this.sdt = sdt;
    }
}
