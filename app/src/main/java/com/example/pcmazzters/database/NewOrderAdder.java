package com.example.pcmazzters.database;

import android.os.AsyncTask;

import com.example.pcmazzters.activity.new_order_adder.ModelChooserActivity;
import com.example.pcmazzters.activity.new_order_adder.ProblemDescriberActivity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Date;
import java.sql.DriverManager;
import java.util.Properties;


public class NewOrderAdder extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... params) {

        String url = params[0];
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "HSQxxd83918");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");

        Connection cn = null;
        Statement st = null;

        try {
           DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            cn = (Connection) DriverManager.getConnection(url, prop);
            st = (Statement) cn.createStatement();
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("insert into new_orders values(null,?,?,?,?,?,?)");
            pst.setString(1, ModelChooserActivity.deviceNameToDB);
            pst.setString(2, ProblemDescriberActivity.problemToDB);
            pst.setString(3, ProblemDescriberActivity.customerToDB);
            pst.setString(4, ProblemDescriberActivity.phoneToDB);
            pst.setString(5, ProblemDescriberActivity.emailToDB);
            pst.setDate(6, Date.valueOf(ProblemDescriberActivity.registrationDateToDB));
            pst.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return null;
    }
}
