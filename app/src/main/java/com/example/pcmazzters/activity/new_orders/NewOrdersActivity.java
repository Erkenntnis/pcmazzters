package com.example.pcmazzters.activity.new_orders;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pcmazzters.R;
import com.example.pcmazzters.activity.LoginActivity;
import com.example.pcmazzters.activity.MenuActivity;
import com.example.pcmazzters.database.NewOrderGetter;
import com.example.pcmazzters.database.OperationChooserGetter;

import java.util.ArrayList;

public class NewOrdersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    Toolbar toolbar;
    public static int temp;


    public static ListView lv;
    ArrayAdapter<String> adapter;
    public static ArrayList<String> array = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_orders);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        lv = (ListView) findViewById(R.id.listViewCurrentOrder);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
        lv.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.action_bar_current_orders);
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
        Toast toast = Toast.makeText(getApplicationContext(), NewOrderGetter.arrayNewOrder.get(position).toString(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        temp = position;
        OperationChooserGetter to = new OperationChooserGetter();
        to.execute(LoginActivity.urldb);
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, OperationChooserActivity.class);
        startActivity(intent);
//        Toast.makeText(getApplicationContext(), "Ololo", Toast.LENGTH_LONG).show();
        return true;
    }
}
