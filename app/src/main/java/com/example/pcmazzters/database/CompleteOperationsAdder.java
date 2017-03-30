package com.example.pcmazzters.database;

import android.os.AsyncTask;

import com.example.pcmazzters.activity.new_orders.NewOrdersActivity;
import com.example.pcmazzters.activity.LoginActivity;
import com.example.pcmazzters.activity.new_orders.OperationChooserActivity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Date;
import java.sql.DriverManager;
import java.util.Properties;

public class CompleteOperationsAdder extends AsyncTask<String, Integer, String> {

    int id_user;

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

        for (int i=0; i < UserGetter.arrayUser.size(); i++){
            if(UserGetter.arrayUser.get(i).getLogin().equals(LoginActivity.user)){
                id_user = UserGetter.arrayUser.get(i).getId();
            }
        }

        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            cn = (Connection) DriverManager.getConnection(url, prop);
            st = (Statement) cn.createStatement();
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement("insert into complete_operations values(?,?,?,?,?,null)");

            pst.setInt(1, NewOrderGetter.arrayNewOrder.get(NewOrdersActivity.temp).getId());
            pst.setDate(2, Date.valueOf(OperationChooserActivity.dateCompleteToDB));
            pst.setInt(3, OperationChooserGetter.arrayOrderComplete.get(OperationChooserActivity.temp).getId_operation());
            pst.setDouble(4, OperationChooserGetter.arrayOrderComplete.get(OperationChooserActivity.temp).getCost());
            pst.setInt(5, id_user);
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
