package com.nuzzel.jay.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jay on 9/30/14.
 */
public class CustomTaskAdapter extends ArrayAdapter<String> {
    private ArrayList<String> taskList;
    private final Context context;

    public CustomTaskAdapter(Context context, int textViewResourceId, ArrayList<String> taskList) {
        super (context, textViewResourceId, taskList);
        this.taskList = new ArrayList<String>();
        this.taskList.addAll(taskList);
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView task = (TextView) rowView.findViewById(R.id.tvTask);
        task.setText(taskList.get(position));
/*
        rowView.findViewById(R.id.cbCheckoff).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                CheckBox box = (CheckBox) v;
                box.isChecked();
            }
        });

*/
        return rowView;
    }

    public void add(String object) {
        super.add(object);
        taskList.add(object);
    }
}
