package com.example.hoang.kaitea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Switch;

import com.example.hoang.kaitea.data.ClockAdapter;
import com.example.hoang.kaitea.data.Timer;

import java.util.ArrayList;
import java.util.List;

public class ClockAcitivity extends AppCompatActivity
{
    ListView mainListView;
    Switch onTea;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_acitivity);
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        List<Timer> list = getList();
        ClockAdapter clockAdapter = new ClockAdapter(this, list);
        mainListView = (ListView) findViewById(R.id.clock_list_view);
        mainListView.setAdapter(clockAdapter);
        



    }

    private List<Timer> getList()
    {
        List<Timer> list = new ArrayList<>();
        long oneMinute = 60000;
        Timer nhai = new Timer("Nhài", oneMinute*3/60, false);
        Timer den = new Timer("Đen", oneMinute*12,false);
        Timer olong = new Timer("Ôlong", oneMinute*10,false);
        Timer kieumach = new Timer("Kiều Mạch", oneMinute*10,false);
        Timer sencha = new Timer("Sencha", oneMinute*10,false);
        Timer matcha = new Timer("Matcha", oneMinute*3,false);
        Timer nautranchau= new Timer("Nấu TC", oneMinute*35,false);
        Timer utranchau= new Timer("Ủ TC", oneMinute*20,false);


        list.add(nhai);
        list.add(den);
        list.add(olong);
        list.add(kieumach);
        list.add(sencha);
        list.add(matcha);
        list.add(nautranchau);
        list.add(utranchau);
        return list;
    }


}
