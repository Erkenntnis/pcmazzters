package com.example.pcmazzters.database;

import android.os.AsyncTask;

import com.example.pcmazzters.activity.new_orders.NewOrdersActivity;
import com.example.pcmazzters.activity.new_orders.OperationChooserActivity;
import com.example.pcmazzters.classes.Operation;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

public class OperationChooserGetter extends AsyncTask<String, Integer, String> {

    String modelName = NewOrderGetter.arrayNewOrder.get(NewOrdersActivity.temp).getModel();
    public static ArrayList<Operation> arrayOrderComplete;

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

        Operation operation;
        arrayOrderComplete = new ArrayList<>();
        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            cn = (Connection) DriverManager.getConnection(url, prop);
            st = (Statement) cn.createStatement();
            rs = (ResultSet) st.executeQuery("SELECT * FROM operation WHERE model like '"+modelName+"'");

            while (rs.next()) {
                operation = new Operation(rs.getInt("id_operation"),
                    rs.getString("model"),
                    rs.getString("description"),
                    rs.getDouble("cost"));
                arrayOrderComplete.add(operation);
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

        @Override
    protected void onPostExecute(String s) {

        for(int i=0; i<arrayOrderComplete.size();i++){
            OperationChooserActivity.array.add(arrayOrderComplete.get(i).getDescription());
        }

        super.onPostExecute(s);
    }
}
