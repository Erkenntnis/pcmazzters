package com.example.pcmazzters.database;

import android.os.AsyncTask;

import com.example.pcmazzters.classes.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

public class UserGetter extends AsyncTask<String, Integer, String> {

    public static ArrayList<User> arrayUser;

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

        User user;
        arrayUser = new ArrayList<>();

        try {

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            cn = (Connection) DriverManager.getConnection(url, prop);
            st = (Statement) cn.createStatement();
            rs = (ResultSet) st.executeQuery("Select * from users");

            while (rs.next()) {
                user = new User(rs.getInt("id_user"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"));
                arrayUser.add(user);
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
