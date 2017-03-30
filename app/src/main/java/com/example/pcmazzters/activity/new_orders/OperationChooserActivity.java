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
import android.widget.TextView;
import android.widget.Toast;

import com.example.pcmazzters.R;
import com.example.pcmazzters.activity.CompleteOrders;
import com.example.pcmazzters.activity.LoginActivity;
import com.example.pcmazzters.activity.MenuActivity;
import com.example.pcmazzters.activity.ProfileActivity;
import com.example.pcmazzters.database.CostGetter;
import com.example.pcmazzters.database.NewOrderGetter;
import com.example.pcmazzters.database.OrderReplacer;
import com.example.pcmazzters.database.CompleteOrderGetter;
import com.example.pcmazzters.database.CompleteOrderAdder;
import com.example.pcmazzters.database.CompleteOperationsAdder;
import com.example.pcmazzters.database.ProfileGetter;
import com.example.pcmazzters.database.TotalCostProfileGetter;

import java.util.Calendar;
import java.util.LinkedList;

public class OperationChooserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    Toolbar toolbar;
    ListView lv;
    ArrayAdapter<String> adapter;
//    public static ArrayList<String> array = new ArrayList<String>();
    public static LinkedList<String> array = new LinkedList<String>();
    public static String dateCompleteToDB;
    public static int operationId, orderCost;
    public static int temp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_chooser);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        lv = (ListView) findViewById(R.id.listViewOperationChooser);
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.action_bar_operation_chooser);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        Calendar c = Calendar.getInstance();
        int year=c.get(c.YEAR);
        int month=c.get(c.MONTH)+1;
        int day=c.get(c.DAY_OF_MONTH);
        dateCompleteToDB = year+"-"+month+"-"+day;
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
                Toast toast = Toast.makeText(getApplicationContext(),"Please, complete this section before you leave", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Toast toast = Toast.makeText(getApplicationContext(),"Please, complete this section before you leave", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        temp = (int) id;

        if(!((TextView)view).getText().equals("")){
            CompleteOperationsAdder tco = new CompleteOperationsAdder();
            tco.execute(LoginActivity.urldb);

            ((TextView)view).setText("");
            Toast toast = Toast.makeText(getApplicationContext(),"Operation has been added", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        if(((TextView)view).getText().equals("")){
        }
    }

    public void buttonChooseOperation(View v){
        adapter.clear();
        CompleteOrders.array.clear();
        NewOrdersActivity.array.clear();
        ProfileActivity.array.clear();

        CostGetter cg = new CostGetter();
        cg.execute(LoginActivity.urldb);

        CompleteOrderAdder tcdba = new CompleteOrderAdder();
        tcdba.execute(LoginActivity.urldb);

        OrderReplacer or = new OrderReplacer();
        or.execute(LoginActivity.urldb);

        CompleteOrderGetter tc = new CompleteOrderGetter();
        tc.execute(LoginActivity.urldb);

        NewOrderGetter gfd = new NewOrderGetter();
        gfd.execute(LoginActivity.urldb);

        ProfileGetter tp = new ProfileGetter();
        tp.execute(LoginActivity.urldb);

        TotalCostProfileGetter tcpg = new TotalCostProfileGetter();
        tcpg.execute(LoginActivity.urldb);

        Toast toast = Toast.makeText(getApplicationContext(),"Status has been changed", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
//        Toast.makeText(getApplicationContext(),"Order id: "+NewOrderGetter.arrayNewOrder.get(NewOrdersActivity.temp).getId(), Toast.LENGTH_SHORT).show();

    }
}
