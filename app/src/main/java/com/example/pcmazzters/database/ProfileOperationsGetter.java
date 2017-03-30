package com.example.pcmazzters.database;

import android.os.AsyncTask;

import com.example.pcmazzters.activity.ProfileActivity;
import com.example.pcmazzters.classes.CompleteOperations;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

public class ProfileOperationsGetter extends AsyncTask<String, Integer, String> {

    public static ArrayList<CompleteOperations> arrayOperationComplete;

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

        CompleteOperations completeOperations;
        arrayOperationComplete = new ArrayList<>();

        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            cn = (Connection) DriverManager.getConnection(url, prop);
            st = (Statement) cn.createStatement();
            rs = (ResultSet) st.executeQuery("SELECT description, complete_operations.cost" +
                    " FROM complete_operations, operation" +
                    " WHERE complete_operations.id_operation = operation.id_operation"+
                    " AND complete_operations.id_order = "+ ProfileGetter.arrayOrderProfile.get(ProfileActivity.temp2).getId()+"");

            while (rs.next()) {
                completeOperations = new CompleteOperations(rs.getString("description"),
                        rs.getDouble("cost"));
                arrayOperationComplete.add(completeOperations);
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
