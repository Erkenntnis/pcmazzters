package com.example.pcmazzters.database;

import android.os.AsyncTask;

import com.example.pcmazzters.activity.new_orders.NewOrdersActivity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.util.Properties;

public class OrderReplacer extends AsyncTask<String, Integer, String> {
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
                PreparedStatement pst = (PreparedStatement) cn.prepareStatement("DELETE FROM new_orders WHERE id_new_order = ?");
                pst.setInt(1, NewOrderGetter.arrayNewOrder.get(NewOrdersActivity.temp).getId());
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

