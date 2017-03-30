package com.example.pcmazzters.activity;

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
import com.example.pcmazzters.database.OperationsGetter;
import com.example.pcmazzters.database.CompleteOrderGetter;

import java.util.ArrayList;

public class CompleteOrders extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    Toolbar toolbar;
//    DrawerLayout d_layout;
//    LinearLayout drawer;
//    ActionBarDrawerToggle toggle;
    public static int temp;
    public static int temp2;

    ListView lv;
    public static ArrayAdapter<String> adapter;
    public static ArrayList<String> array = new ArrayList<String>();
    public static ArrayList<String> arrayOperations = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_orders);

        toolbar = (Toolbar) findViewById(R.id.action_bar_complete_orders);
        setSupportActionBar(toolbar);
//        d_layout = (DrawerLayout) findViewById(R.id.drawerComplete_layout);
//        drawer = (LinearLayout) findViewById(R.id.drawerComplete);
//        toggle = new ActionBarDrawerToggle(this, d_layout, toolbar, R.string.opened, R.string.closed);
//        d_layout.addDrawerListener(toggle);
//        toggle.syncState();

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        lv = (ListView) findViewById(R.id.listViewCompleteOrder);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
        lv.setAdapter(adapter);

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
        temp = position;
        Toast toast = Toast.makeText(getApplicationContext(), CompleteOrderGetter.arrayOrderComplete.get(CompleteOrders.temp).toString(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        temp2 = position;
        OperationsGetter og = new OperationsGetter();
        og.execute(LoginActivity.urldb);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast toast = Toast.makeText(getApplicationContext(), "Operations:\n"+OperationsGetter.arrayOperationComplete.toString(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return true;
    }
}
