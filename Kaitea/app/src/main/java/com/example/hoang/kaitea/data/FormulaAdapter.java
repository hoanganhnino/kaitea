package com.example.hoang.kaitea.data;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.hoang.kaitea.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HOANG on 8/1/2018.
 */

public class FormulaAdapter extends BaseExpandableListAdapter
{

    private Context mContext;
    private List<String> mHeaderGroup;
    private HashMap<String, List<String>> mDataChild;
    ExpandableListView expandableListView;

    public FormulaAdapter(Context context, List<String> headerGroup, HashMap<String, List<String>> data)
    {
        mContext = context;
        mHeaderGroup = headerGroup;
        mDataChild = data;
    }

    @Override
    public int getGroupCount()
    {
        return mHeaderGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return mDataChild.get(mHeaderGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return mHeaderGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return this.mDataChild.get(this.mHeaderGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater li = LayoutInflater.from(mContext);
            convertView = li.inflate(R.layout.formula_group, parent, false);
        }



        TextView tvHeader = (TextView) convertView.findViewById(R.id.group_text_view);
        tvHeader.setText(mHeaderGroup.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        /**if (convertView == null) {
         LayoutInflater li = LayoutInflater.from(mContext);
         convertView = li.inflate(R.layout.list_child, parent, false);
         }
         String childText = (String)getChild(groupPosition,childPosition);
         */
        final String childText = (String) getChild(groupPosition, childPosition);
        Spanned result = Html.fromHtml(childText);
        LayoutInflater infalInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
        {
            convertView = infalInflater.inflate(R.layout.formula_child, null);
        }
        TextView tvChild = (TextView) convertView.findViewById(R.id.child_text_view);

        tvChild.setText(result);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }


}

