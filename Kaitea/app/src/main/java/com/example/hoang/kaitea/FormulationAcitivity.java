package com.example.hoang.kaitea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.example.hoang.kaitea.data.FormulaAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormulationAcitivity extends AppCompatActivity
{
    ExpandableListView expandableListView;
    FormulaAdapter adapter;
    HashMap<String, List<String>> mData;
    List<String> listHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulation_acitivity);

        expandableListView = (ExpandableListView)findViewById(R.id.formula_expand_list);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
        {
            int previousItem = -1;
            @Override
            public void onGroupExpand(final int groupPosition)
            {

            }
        });
        prepareList();

        adapter = new FormulaAdapter(this, listHeader, mData);
        expandableListView.setAdapter(adapter);

    }

    private void prepareList()
    {
        listHeader = new ArrayList<>();
        mData = new HashMap<String, List<String>>();

        listHeader.add(getResources().getString(R.string.fresh_milk_tea));
        listHeader.add(getResources().getString(R.string.classic_milk_tea));
        listHeader.add(getResources().getString(R.string.fruit_tea));
        listHeader.add(getResources().getString(R.string.brewed_tea));
        listHeader.add(getResources().getString(R.string.cheese_tea));
        listHeader.add(getResources().getString(R.string.topping));
        listHeader.add(getResources().getString(R.string.u_tra));

        List<String> listFreshMilkTea = new ArrayList<>();
        listFreshMilkTea.add(getResources().getString(R.string.sencha_fresh));
        listFreshMilkTea.add(getResources().getString(R.string.olong_fresh));
        listFreshMilkTea.add(getResources().getString(R.string.buckwheat_fresh));
        listFreshMilkTea.add(getResources().getString(R.string.matcha_fresh));
        listFreshMilkTea.add(getResources().getString(R.string.note_fresh));

        List<String> listClassicMilkTea = new ArrayList<>();
        listClassicMilkTea.add(getResources().getString(R.string.sencha_classic));
        listClassicMilkTea.add(getResources().getString(R.string.jasmine_classic));
        listClassicMilkTea.add(getResources().getString(R.string.assam_classic));
        listClassicMilkTea.add(getResources().getString(R.string.olong_classic));
        listClassicMilkTea.add(getResources().getString(R.string.socola));
        listClassicMilkTea.add(getResources().getString(R.string.note_classic));

        List<String> listFruitTea = new ArrayList<>();
        listFruitTea.add(getResources().getString(R.string.cam_xa));
        listFruitTea.add(getResources().getString(R.string.raspberry));
        listFruitTea.add(getResources().getString(R.string.olong_dao));
        listFruitTea.add(getResources().getString(R.string.chanh_leo));
        listFruitTea.add(getResources().getString(R.string.lychee));
        listFruitTea.add(getResources().getString(R.string.note_fruit));

        List<String> lisBrewedTea = new ArrayList<>();
        lisBrewedTea.add(getResources().getString(R.string.nhai));
        lisBrewedTea.add(getResources().getString(R.string.kieu_mach));

        List<String> listCheeseTea = new ArrayList<>();
        listCheeseTea.add(getResources().getString(R.string.nhai_cheese));
        listCheeseTea.add(getResources().getString(R.string.olong_cheese));
        listCheeseTea.add(getResources().getString(R.string.xoai_cheese));
        listCheeseTea.add(getResources().getString(R.string.matcha_cheese_cake));
        listCheeseTea.add(getResources().getString(R.string.socola_cheese_cake));
        listCheeseTea.add(getResources().getString(R.string.note_cheese));

        List<String> listTopping = new ArrayList<>();
        listTopping.add(getResources().getString(R.string.tcd));
        listTopping.add(getResources().getString(R.string.matcha));
        listTopping.add(getResources().getString(R.string.pudding));
        listTopping.add(getResources().getString(R.string.thach_tuoi));
        listTopping.add(getResources().getString(R.string.cheese));
        listTopping.add(getResources().getString(R.string.cheese_cake));
        listTopping.add(getResources().getString(R.string.note_topping));

        List<String> listUTra = new ArrayList<>();
        listUTra.add(getResources().getString(R.string.u_nhai));
        listUTra.add(getResources().getString(R.string.u_den));
        listUTra.add(getResources().getString(R.string.u_olong));
        listUTra.add(getResources().getString(R.string.u_sencha));
        listUTra.add(getResources().getString(R.string.u_kieu_mach));
        listUTra.add(getResources().getString(R.string.note_u_tra));

        mData.put(listHeader.get(0), listFreshMilkTea);
        mData.put(listHeader.get(1), listClassicMilkTea);
        mData.put(listHeader.get(2), listFruitTea);
        mData.put(listHeader.get(3), lisBrewedTea);
        mData.put(listHeader.get(4), listCheeseTea);
        mData.put(listHeader.get(5), listTopping);
        mData.put(listHeader.get(6), listUTra);
    }

    /*public String spannable(String s)
    {
        SpannableString span = new SpannableString(s);
        span.setSpan
        return new SpannableString(s);
    }*/
}
