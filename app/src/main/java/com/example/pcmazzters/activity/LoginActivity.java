package com.example.pcmazzters.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pcmazzters.R;
import com.example.pcmazzters.classes.User;
import com.example.pcmazzters.database.NewOrderGetter;
import com.example.pcmazzters.database.CompleteOrderGetter;
import com.example.pcmazzters.database.ModelGetter;
import com.example.pcmazzters.database.ProfileGetter;
import com.example.pcmazzters.database.TotalCostProfileGetter;
import com.example.pcmazzters.database.UserGetter;

import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUser;
    EditText editTextPassword;
    SecretKeySpec secret_key;
    IvParameterSpec iv;
    static Cipher cipher;

    public static ArrayList<User> array = new ArrayList<>();
    public static final String urldb = "jdbc:mysql://31.130.206.241:3306/pcmazzters";
    static final String key = "fsdasdfaYKJH878HL88aSFng";
    static final String ivector = "pogcgrdlmnvcrubc";
    public static String user, password;
    String userTemp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        UserGetter ug = new UserGetter();
        ug.execute(urldb);

        secret_key = new SecretKeySpec(key.getBytes(), "AES");
        iv = new IvParameterSpec(ivector.getBytes());

        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buttonLogin(View v) {

        String inputLogin = editTextUser.getText().toString();
        String inputPassword = editTextPassword.getText().toString();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secret_key, iv);
            byte[] resultLogin = cipher.doFinal(inputLogin.getBytes());
            byte[] resultPassword = cipher.doFinal(inputPassword.getBytes());
            user = Base64.encodeToString(resultLogin, Base64.DEFAULT);
            password = Base64.encodeToString(resultPassword, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (inputLogin.equals("") || inputPassword.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please, fill all the textfields", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {

            for (int i = 0; i < UserGetter.arrayUser.size(); i++) {
                if (UserGetter.arrayUser.get(i).getLogin().equals(user)) {
                    userTemp = UserGetter.arrayUser.get(i).getLogin();
                }
            }
            if (userTemp.equals("")) {
//                if (!inputLogin.equals("") && userTemp.equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Unknown user", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            userTemp = "";

            for (int i = 0; i < UserGetter.arrayUser.size(); i++) {
                if (UserGetter.arrayUser.get(i).getLogin().equals(user)) {
                    if (UserGetter.arrayUser.get(i).getPassword().equals(password)) {
                        //Обработка нажатия кнопки
                        NewOrderGetter gfd = new NewOrderGetter();
                        gfd.execute(urldb);
                        CompleteOrderGetter tc = new CompleteOrderGetter();
                        tc.execute(urldb);
                        ProfileGetter tp = new ProfileGetter();
                        tp.execute(urldb);
                        ModelGetter tm = new ModelGetter();
                        tm.execute(urldb);
                        TotalCostProfileGetter tcpg = new TotalCostProfileGetter();
                        tcpg.execute(urldb);
                        Intent intent = new Intent(this, MenuActivity.class);
                        startActivity(intent);
                        break;
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        }
    }
}
