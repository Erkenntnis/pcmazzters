package com.example.pcmazzters.database;

import android.os.AsyncTask;

import com.example.pcmazzters.activity.LoginActivity;
import com.example.pcmazzters.activity.ProfileActivity;
import com.example.pcmazzters.classes.CompleteOrder;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

public class ProfileGetter extends AsyncTask<String, Integer, String> {

    public static ArrayList<CompleteOrder> arrayOrderProfile;
    public static ArrayList<String> arrayNameProfile;
    public static String name;

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

        CompleteOrder orderProfile;

        arrayOrderProfile = new ArrayList<>();
        arrayNameProfile = new ArrayList<>();

        try {

            String user = LoginActivity.user;

            for (int i=0; i < UserGetter.arrayUser.size(); i++){
                if(UserGetter.arrayUser.get(i).getLogin().equals(user)){
                    name = UserGetter.arrayUser.get(i).getName();
                }
            }

            Calendar c = Calendar.getInstance();
            int year=c.get(c.YEAR);
            int month=c.get(c.MONTH)+1;

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            cn = (Connection) DriverManager.getConnection(url, prop);
            st = (Statement) cn.createStatement();
            rs = (ResultSet) st.executeQuery("SELECT id_complete_order, model, problem, customer, customer_phone, customer_email, date_completement, name, cost" +
                    " FROM complete_order, users" +
                    " WHERE complete_order.id_user = users.id_user" +
                    " AND date_completement > '"+year+"-"+month+"-01'" +
                    " AND users.name like '"+name+"'");

            while (rs.next()) {
                orderProfile = new CompleteOrder(rs.getInt("id_complete_order"),
                        rs.getString("model"),
                        rs.getString("problem"),
                        rs.getString("customer"),
                        rs.getString("customer_phone"),
                        rs.getString("customer_email"),
                        rs.getDate("date_completement").toString(),
                        rs.getString("name"),
                        rs.getDouble("cost"));
                arrayOrderProfile.add(orderProfile);
                ProfileActivity.array.add(rs.getString("model"));
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
