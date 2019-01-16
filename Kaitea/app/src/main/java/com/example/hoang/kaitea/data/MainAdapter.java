package com.example.hoang.kaitea.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.kaitea.R;

import java.util.List;

/**
 * Created by HOANG on 8/27/2018.
 */

public class MainAdapter extends BaseAdapter
{

    private List<MainItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;



    public MainAdapter(Context mContext,  List<MainItem> listData)
    {

        this.context = mContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(mContext);
    }
    public int getCount()
    {
        return listData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.main_item_layout, null);
            holder = new ViewHolder();
            holder.imgageView = (ImageView) convertView.findViewById(R.id.image_main_item);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.text_view_main_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MainItem mainItem = this.listData.get(position);
        holder.nameTextView.setText(mainItem.getName());

        String imgName = "R.drawable"+mainItem.getImg();
        int imageId = this.getDrawableIDbyName(mainItem.getImg());

        holder.imgageView.setImageResource(imageId);

        return convertView;
    }

    static class ViewHolder {
        ImageView imgageView;
        TextView nameTextView;

    }

    public int getDrawableIDbyName(String resName)
    {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
}
