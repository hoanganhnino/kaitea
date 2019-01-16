package com.example.hoang.kaitea.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hoang.kaitea.R;

import java.util.List;

/**
 * Created by HOANG on 9/27/2018.
 */

public class NoteAdapter extends ArrayAdapter<Note>
{
    private Context context;
    private List<Note> listNote;
    public NoteAdapter(@NonNull Context context, List<Note> listNote)
    {
        super(context, R.layout.note_item_layout, listNote);
        this.context = context;
        this.listNote = listNote;
    }

    public View getView(final int position, View view, ViewGroup parent)
    {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.note_item_layout,null, true);
        TextView titleTV = (TextView)rowView.findViewById(R.id.title_note_item);
        titleTV.setText(listNote.get(position).getTitle());
        return rowView;
    }

    public void updateData(List<Note> data)
    {
        this.listNote = data;
        notifyDataSetChanged();
    }
}
