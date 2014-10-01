package com.nuzzel.jay.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jay on 9/30/14.
 */
public class CustomTaskAdapter extends ArrayAdapter{
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
        TextView description = (TextView) rowView.findViewById(R.id.description);
        description.setText(taskList.get(position));
        return rowView;
    }

    public void add(String object) {
        super.add(object);
        taskList.add(object);
    }

    public void remove(String object) {
        super.remove(object);
        taskList.remove(object);
    }

    public ArrayList<String> getTaskList() {
        return taskList;
    }
}