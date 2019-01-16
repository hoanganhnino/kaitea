package com.example.hoang.kaitea;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hoang.kaitea.data.Contact;
import com.example.hoang.kaitea.data.ContactAdapter;
import com.example.hoang.kaitea.data.DbHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity
{
    ListView listView;
    List<Contact> listContact;
    ContactAdapter adapter;
    ImageView dot;
    Cursor c;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        listContact = new ArrayList<>();
        listView = (ListView)findViewById(R.id.contact_list_view);
        geContactDB();

        dbHelper = new DbHelper(this);
        /*final List<Contact> list = getList();
        listContact.addAll(list);*/
        adapter = new ContactAdapter(this, listContact);

        listView.setAdapter(adapter);
        //adapter.updateData(listContact);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (ActivityCompat.checkSelfPermission(ContactActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent phoneCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + listContact.get(position).getSdt()));
                    startActivity(phoneCall);
                    return;
                }

//                switch (position)
//                {
//                    case 0:
//                        phoneCall.setData(Uri.parse("tel:" + list.get(position).getSdt()));
//                        break;
//                    case 1:
//                        phoneCall.setData(Uri.parse("tel:" + list.get(position).getSdt()));
//                        break;
//                    case 2:
//                        phoneCall.setData(Uri.parse("tel:" + list.get(position).getSdt()));
//                        break;
//                }
                if (ActivityCompat.checkSelfPermission(ContactActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

            }
        });

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Chọn liên hệ",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 1);
            }
        });


    }


    /*private List<Contact> getList()
    {
        List<Contact> list = new ArrayList<>();
        Contact sepDuc = new Contact("Sếp Đức", "0988329791");
        Contact sepYen = new Contact("Sếp Yên", "0972107391");
        Contact HoangAnh = new Contact("Hoàng Anh", "0984419401");
        Contact LanAnh = new Contact("Lan Anh", "0968857149");
        list.add(sepDuc);
        list.add(sepYen);
        list.add(HoangAnh);
        list.add(LanAnh);
        return list;
    }*/


    public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        String number="";
        if(reqCode == 1){
            if (resultCode == Activity.RESULT_OK) {
                Log.d("ContactsH", "ResOK");
                Uri contactData = data.getData();
                Cursor contact =  getContentResolver().query(contactData, null, null, null, null);

                if (contact.moveToFirst()) {
                    String name = contact.getString(contact.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    // TODO Whatever you want to do with the selected contact's name.

                    ContentResolver cr = getContentResolver();
                    Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                            "DISPLAY_NAME = '" + name + "'", null, null);
                    if (cursor.moveToFirst()) {
                        String contactId =
                                cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        //
                        //  Get all phone numbers.
                        //
                        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                        while (phones.moveToNext()) {
                            number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            switch (type) {
                                case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                    // do something with the Home number here...
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                    // do something with the Mobile number here...
                                    Log.d("ContactsH", number);
                                    break;
                                case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                    // do something with the Work number here...
                                    break;
                            }
                        }
                        listContact.add(new Contact(name,number));
                        Toast.makeText(getApplicationContext(), "Đã thêm "+name+" vào liên hệ",Toast.LENGTH_SHORT).show();

                        dbHelper.insertDB(new Contact(name,number));
                        adapter.updateData(listContact);
                        phones.close();
                    }
                    cursor.close();
                }
            }
        }else{
            Log.d("ContactsH", "Canceled");
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

    }

    public void geContactDB()
    {
        DbHelper myDbHelper = new DbHelper(this);
        try
        {
            myDbHelper.createDataBase();
        } catch (IOException ioe)
        {
            throw new Error("Unable to create database");
        }
        try
        {
            myDbHelper.openDataBase();
        } catch (SQLException sqle)
        {
            throw sqle;
        }
        c = myDbHelper.query("Contact", null, null, null, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast())
        {
            listContact.add(new Contact(c.getString(1), c.getString(2)));
            //justGetQuestion.add(c.getString(1));
            //Toast.makeText(this, "justGetQuestion: "+i,Toast.LENGTH_SHORT).show();
            c.moveToNext();

        }


    }


}
