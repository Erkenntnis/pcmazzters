package com.example.pcmazzters.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.pcmazzters.R;
import com.example.pcmazzters.activity.new_order_adder.ModelChooserActivity;
import com.example.pcmazzters.activity.new_orders.NewOrdersActivity;

public class MenuActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout d_layout;
    LinearLayout drawer;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_menu);

        toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        d_layout = (DrawerLayout) findViewById(R.id.drawerMenu_layout);
        drawer = (LinearLayout) findViewById(R.id.drawerMenu);
        toggle = new ActionBarDrawerToggle(this, d_layout, toolbar, R.string.opened, R.string.closed);
        d_layout.addDrawerListener(toggle);
        toggle.syncState();

    }
    public void menuDrawer1(View v) {
        Intent intent = new Intent(this, NewOrdersActivity.class);
        startActivity(intent);
    }
    public void menuDrawer2(View v) {
        Intent intent = new Intent(this, CompleteOrders.class);
        startActivity(intent);
    }
    public void menuDrawer3(View v) {
        Intent intent = new Intent(this, ModelChooserActivity.class);
        startActivity(intent);
    }
    public void menuDrawer4(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
    public void menuDrawer5(View v) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
    public void menuDrawer6(View v) {
        CompleteOrders.array.clear();
        NewOrdersActivity.array.clear();
        ProfileActivity.array.clear();
        ModelChooserActivity.arrayAAS.clear();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        CompleteOrders.array.clear();
        NewOrdersActivity.array.clear();
        ProfileActivity.array.clear();
        ModelChooserActivity.arrayAAS.clear();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}




