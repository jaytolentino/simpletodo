package com.nuzzel.jay.simpletodo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jay on 9/30/14.
 */

public class CustomTaskAdapter extends ArrayAdapter{
    private ArrayList<Task> taskList;
    private final Context context;

    public CustomTaskAdapter(Context context, int textViewResourceId, ArrayList<Task> taskList) {
        super (context, textViewResourceId, taskList);
        this.taskList = new ArrayList<Task>();
        this.taskList.addAll(taskList);
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        final Task currentTask = taskList.get(position);

        TextView description = (TextView) rowView.findViewById(R.id.description);
        description.setText(currentTask.getDescription());

        CheckBox checkoff = (CheckBox) rowView.findViewById(R.id.checkoff);
        checkoff.setChecked(currentTask.isComplete());
        checkoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTask.checkTask();
                MainActivity activity = (MainActivity) context;
                activity.writeItems();
            }
        });

        return rowView;
    }

    public void add(Task object) {
        super.add(object);
        taskList.add(object);
    }

    public void remove(Task object) {
        super.remove(object);
        taskList.remove(object);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
