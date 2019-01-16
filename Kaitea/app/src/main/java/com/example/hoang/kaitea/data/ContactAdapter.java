package com.example.hoang.kaitea.data;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.kaitea.R;

import java.util.List;

/**
 * Created by HOANG on 9/8/2018.
 */

public class ContactAdapter extends ArrayAdapter<Contact>
{
    private Context context;
    private List<Contact> listContact;
    DbHelper  db = new DbHelper(getContext());
    public static int flag = 0;

   // private Spinner spinner;

    public ContactAdapter(@NonNull Context context, List<Contact> listContact)
    {
        super(context,R.layout.contact_item_layout,listContact);
        this.context = context;
        this.listContact = listContact;

    }



    public View getView(final int position, View view, ViewGroup parent)
    {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.contact_item_layout,null, true);
        TextView nameTextView = (TextView)rowView.findViewById(R.id.name_text_view);
        TextView sdtTextView = (TextView)rowView.findViewById(R.id.sdt_text_view);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.dot_imge_view);
        //mySpinner(rowView);
        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(getContext(), "Clicked "+position, Toast.LENGTH_SHORT).show();
                confirmDelete(position);
                notifyDataSetChanged();
                //updateData(listContact);
               /* if (isiJustWantToDelete())
                db.deleteContact(listContact.get(position),position);
                Toast.makeText(getContext(), "confirm : "+isiJustWantToDelete(),Toast.LENGTH_SHORT).show();*/
                //spinner.performClick();
            }
        });

        nameTextView.setText(listContact.get(position).getName());
        sdtTextView.setText(listContact.get(position).getSdt());
        return rowView;
    }

    public void updateData(List<Contact> data)
    {
        this.listContact = data;
        notifyDataSetChanged();
    }


    public void confirmDelete(final int pos)
    {

        new android.support.v7.app.AlertDialog.Builder(getContext())
                .setMessage("Đồng ý xóa liên hệ này ?")
                .setCancelable(false)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.deleteContact(listContact.get(pos),pos);

                        remove(listContact.get(pos));
                        //updateData(listContact);
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //flag++;
                        Toast.makeText(getContext(), "Hủy", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }
    /*public void mySpinner(View view)
    {
        spinner = (Spinner)view.findViewById(R.id.option_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.option_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        Toast.makeText(getContext(),"Sửa", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getContext(),"Xoa", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }


        });
    }*/


}
