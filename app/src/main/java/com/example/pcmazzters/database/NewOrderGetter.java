package com.example.pcmazzters.database;

import android.os.AsyncTask;

import com.example.pcmazzters.activity.new_orders.NewOrdersActivity;
import com.example.pcmazzters.classes.NewOrder;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

public class NewOrderGetter extends AsyncTask<String, Integer, String> {

    public static ArrayList<NewOrder> arrayNewOrder;
    public static ArrayList<Integer> idCollector;

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

        NewOrder newOrder;
        arrayNewOrder = new ArrayList<>();
        idCollector = new ArrayList<>();
        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            cn = (Connection) DriverManager.getConnection(url, prop);
            st = (Statement) cn.createStatement();
            rs = (ResultSet) st.executeQuery("SELECT * FROM new_orders");

                while (rs.next()) {
                    newOrder = new NewOrder(rs.getInt("id_new_order"),
                            rs.getString("model"),
                            rs.getString("problem"),
                            rs.getString("customer"),
                            rs.getString("customer_phone"),
                            rs.getString("customer_email"),
                            rs.getDate("date_registration").toString());
                    arrayNewOrder.add(newOrder);
                    NewOrdersActivity.array.add(rs.getString("model"));
                    idCollector.add(rs.getInt("id_new_order"));
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


