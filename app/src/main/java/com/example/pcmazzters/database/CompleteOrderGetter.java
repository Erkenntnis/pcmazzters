package com.example.pcmazzters.database;

import android.os.AsyncTask;

import com.example.pcmazzters.activity.CompleteOrders;
import com.example.pcmazzters.classes.CompleteOrder;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

public class CompleteOrderGetter extends AsyncTask<String, Integer, String> {

    public static ArrayList<CompleteOrder> arrayOrderComplete;

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
        ResultSet rs = null;

        CompleteOrder orderComplete;
        arrayOrderComplete = new ArrayList<>();

        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            cn = (Connection) DriverManager.getConnection(url, prop);
            st = (Statement) cn.createStatement();
            rs = (ResultSet) st.executeQuery("SELECT id_complete_order, model, problem, customer, customer_phone, customer_email, date_completement, name, cost" +
                    " FROM complete_order, users" +
                    " WHERE complete_order.id_user = users.id_user");

            while (rs.next()) {
                orderComplete = new CompleteOrder(rs.getInt("id_complete_order"),
                        rs.getString("model"),
                        rs.getString("problem"),
                        rs.getString("customer"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getDate("date_completement").toString(),
                        rs.getString("name"),
                        rs.getDouble("cost"));
                arrayOrderComplete.add(orderComplete);
                CompleteOrders.array.add(rs.getString("model"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
