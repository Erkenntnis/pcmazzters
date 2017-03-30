package com.example.pcmazzters.activity.new_order_adder;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pcmazzters.R;
import com.example.pcmazzters.activity.MenuActivity;

import java.util.ArrayList;

public class ModelChooserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    Toolbar toolbar;
    public static String deviceNameToDB;

    ListView lv;
    ArrayAdapter<String> adapter;
    public static ArrayList<String> arrayAAS = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_chooser);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayAAS);
        lv = (ListView) findViewById(R.id.listViewModel);
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.action_bar_model_chooser);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
//                ModelChooserActivity.arrayAAS.clear();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        deviceNameToDB = arrayAAS.get(position);
        Intent intent = new Intent(this, ProblemDescriberActivity.class);
        startActivity(intent);

    }
}
