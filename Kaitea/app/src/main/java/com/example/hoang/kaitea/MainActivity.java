package com.example.hoang.kaitea;
/**
 * started: 27/8
 * 28/8:  layout clock,
 * 31/8: xử lý nấu trân châu: đồng hồ đặt 55', khi đếm đc 35' sẽ hiện alertdialog, wait, khi nhấn oke thì đồng hồ chạy tiếp.
 * 5/9: xu ly nau tran chau
 * 3/10: Note. chức năng lưu, sửa
 * 4/10: Note: delete
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.hoang.kaitea.data.MainAdapter;
import com.example.hoang.kaitea.data.MainItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static int READ_CONTACTS_PERMISSIONS_REQUEST = 100;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS};
        if (!hasPermissions(getApplicationContext(), PERMISSIONS))
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(PERMISSIONS, READ_CONTACTS_PERMISSIONS_REQUEST);
            }
        }

        List<MainItem> list = getList();
        final GridView mainGridView = (GridView) findViewById(R.id.main_grid_view);
        mainGridView.setAdapter(new MainAdapter(this, list));
        mainGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        intent = new Intent(getApplicationContext(), FormulationAcitivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(getApplicationContext(), ClockAcitivity.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent = new Intent(getApplicationContext(), MissionActivity.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(getApplicationContext(), TestActivity.class);
                        startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(getApplicationContext(), NoteActivity.class);
                        startActivity(intent);
                        break;

                    case 5:
                        intent = new Intent(getApplicationContext(), ContactActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private static boolean hasPermissions(Context context, String... permissions)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null)
        {
            for (String permission : permissions)
            {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private List<MainItem> getList()
    {
        List<MainItem> list = new ArrayList<>();
        MainItem congthuc = new MainItem("Công thức", "congthuc");
        MainItem dongho = new MainItem("Đồng hồ", "dongho");
        MainItem nhiemvu = new MainItem("Đăng kí ca", "nhiemvu");
        MainItem test = new MainItem("Test", "test");
        MainItem note = new MainItem("Ghi chú", "note");
        MainItem danhba = new MainItem("Danh bạ", "danhba");

        list.add(congthuc);
        list.add(dongho);
        list.add(nhiemvu);
        list.add(test);
        list.add(note);
        list.add(danhba);


        return list;
    }
}
