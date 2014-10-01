package com.nuzzel.jay.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EditItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_item, menu);
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

    public void onSave(View v) {
        EditText revisedTask = (EditText) findViewById(R.id.revisedTask);
        String taskText = revisedTask.getText().toString();
        Toast.makeText(this, taskText, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(EditItemActivity.this, MainActivity.class);
        i.putExtra("revisedTask", taskText);
        Toast.makeText(this, getIntent().getStringExtra("revisedTask"), Toast.LENGTH_SHORT).show();
        i.putExtra("position", getIntent().getIntExtra("position", 0));
        startActivity(i);
    }
}
