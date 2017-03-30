package com.example.pcmazzters.activity.new_order_adder;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.pcmazzters.R;
import com.example.pcmazzters.activity.new_orders.NewOrdersActivity;
import com.example.pcmazzters.activity.LoginActivity;
import com.example.pcmazzters.activity.MenuActivity;
import com.example.pcmazzters.activity.ProfileActivity;
import com.example.pcmazzters.database.NewOrderAdder;
import com.example.pcmazzters.database.NewOrderGetter;
import com.example.pcmazzters.database.ModelGetter;
import com.example.pcmazzters.database.ProfileGetter;
import com.example.pcmazzters.database.TotalCostProfileGetter;

import java.util.Calendar;

public class ProblemDescriberActivity extends AppCompatActivity {

    Toolbar toolbar;

    public static String problemToDB;
    public static String customerToDB;
    public static String phoneToDB;
    public static String registrationDateToDB;
    public static String emailToDB;

    EditText problem, customer, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_describer);

        problem = (EditText) findViewById(R.id.editTextProblemDescriber);
        customer = (EditText) findViewById(R.id.editTextCustomerName);
        phone = (EditText) findViewById(R.id.editTextCustomerPhone);
        email = (EditText) findViewById(R.id.editTextCustomerEmail);

        toolbar = (Toolbar) findViewById(R.id.action_bar_problem_describer);
        setSupportActionBar(toolbar);
        problem = (EditText) findViewById(R.id.editTextProblemDescriber);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
    }

    public void buttonProblem(View v) {
        problemToDB = problem.getText().toString();
        phoneToDB = phone.getText().toString();
        customerToDB = customer.getText().toString();
        emailToDB = email.getText().toString();

        Calendar c = Calendar.getInstance();

        int year=c.get(c.YEAR);
        int month=c.get(c.MONTH)+1;
        int day=c.get(c.DAY_OF_MONTH);
        registrationDateToDB = year+"-"+month+"-"+day;

        if(     problemToDB.equals("")||
                customerToDB.equals("")||
                phone.equals("")||
                emailToDB.equals("")
                ) {

            Toast toast = Toast.makeText(getApplicationContext(), "Please, fill all the textfields", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {

            NewOrdersActivity.array.clear();
            ModelChooserActivity.arrayAAS.clear();
            ProfileActivity.array.clear();

            NewOrderAdder adt = new NewOrderAdder();
            adt.execute(LoginActivity.urldb);
            NewOrderGetter gfd = new NewOrderGetter();
            gfd.execute(LoginActivity.urldb);
            ModelGetter tm = new ModelGetter();
            tm.execute(LoginActivity.urldb);
            ProfileGetter tp = new ProfileGetter();
            tp.execute(LoginActivity.urldb);
            TotalCostProfileGetter tcpg = new TotalCostProfileGetter();
            tcpg.execute(LoginActivity.urldb);

            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            Toast toast = Toast.makeText(getApplicationContext(), "New order has been added", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
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
}
