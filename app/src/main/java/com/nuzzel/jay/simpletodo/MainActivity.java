package com.nuzzel.jay.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {
    ArrayList<Task> items;
    CustomTaskAdapter itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("To-Do List Manager");

        lvItems = (ListView) findViewById(R.id.lvItems);
        readItems();

        // If intent has extras, replace at position
        if (getIntent().hasExtra("position") && getIntent().hasExtra("revisedTask")) {
            int position = getIntent().getIntExtra("position", 0);
            items.set(position, (Task) getIntent().getSerializableExtra("revisedTask"));
        }

        itemsAdapter = new CustomTaskAdapter(this, R.layout.rowlayout, items);
        writeItems();

        lvItems.setAdapter(itemsAdapter);
        setupLongClickListener();
        setupShortClickListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(new Task(itemText));
        etNewItem.setText("");
        writeItems();
    }

    private void setupLongClickListener() {
        lvItems.setOnItemLongClickListener(
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView,
                                               View view, int pos, long id) {
                    itemsAdapter.remove(items.get(pos));
                    writeItems();
                    return true;
                }
            }
        );
    }

    private void setupShortClickListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int pos, long id) {
                        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                        i.putExtra("position", pos);
                        i.putExtra("oldTask", items.get(pos));
                        startActivity(i);
                    }
                }
        );
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        items = new ArrayList<Task>();
        try {
            for(String line: FileUtils.readLines(todoFile)) {
                List<String> data = Arrays.asList(line.split(" ", 2));
                Task taskToAdd = new Task(data.get(1));
                if(data.get(0).equals("true")) taskToAdd.checkTask();
                items.add(taskToAdd);
            }

        } catch (IOException e) {}
    }

    public void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            ArrayList<String> writableTasks = new ArrayList<String>();
            for(Task t : itemsAdapter.getTaskList()) {
                writableTasks.add(t.getWritable());
            }
            FileUtils.writeLines(todoFile, writableTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
