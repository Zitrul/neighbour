package com.example.samsung.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.samsung.myapp.domain.Login;
import com.example.samsung.myapp.rest.LoginApiService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String message = "";
    public static int auth_id = 0;
    public static final String REST = "REST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity2.class);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        int authStatus = sharedPreferences.getInt("authStatus", -1); // Значение 0 указывает на неуспешную авторизацию
        if (authStatus > -1) {
            auth_id = authStatus;
            //startActivity(intent);
        } else {
            System.out.println("Нужна авотризация");
        }

        /*SharedPreferences mSettings = getSharedPreferences("my_storage", Context.MODE_PRIVATE);
        System.out.println(mSettings.getInt("is_logged",-1));
        if(mSettings.getInt("is_logged",-1) >=0){
            System.out.println("OK");
            startActivity(intent);

        }*/

        setContentView(R.layout.activity_main);




        EditText name = findViewById(R.id.editTextDesc);
        EditText password = findViewById(R.id.editTextSecondName);
        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = name.getText().toString() + "\n" + password.getText().toString();
                if (message != "") {
                    //sendMessage(message);
                    System.out.println(message);
                    LoginApiService.getInstance().getLogin(name.getText().toString(), password.getText().toString()).enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(Call<Login> call, Response<Login> response) {
                            Log.d(REST, response.body().toString());


                            try {
                                if (Integer.parseInt(response.body().getMsg()) >= 0) {

                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("authStatus", Integer.parseInt(response.body().getMsg())); // Значение 1 указывает на успешную авторизацию
                                    editor.apply(); // Применение изменений


                                    auth_id = Integer.parseInt(response.body().getMsg());
                                    startActivity(intent);
                                }

                            } catch (Exception i) {
                                SharedPreferences mSettings = getSharedPreferences("my_storage", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = mSettings.edit();
                                editor.putInt("is_logged", -1).apply();


                                System.out.println(i);
                            }
                        }

                        @Override
                        public void onFailure(Call<Login> call, Throwable t) {
                            Log.d(REST, t.getMessage());
                        }
                    });
                    ;

                }

            }
        });
        Intent intent1 = new Intent(this, Registration.class);
        Button regme = findViewById(R.id.buttonreg);
        regme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent1);
            }
        });

    }


    private void sendMessage(final String msg) {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Socket s = new Socket("192.168.123.55", 5000);

                    OutputStream out = s.getOutputStream();

                    PrintWriter output = new PrintWriter(out);

                    output.println(msg);
                    output.flush();
                    /*BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String st = input.readLine();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String s = mTextViewReplyFromServer.getText().toString();
                            if (st.trim().length() != 0)
                                mTextViewReplyFromServer.setText(s + "\nFrom Server : " + st);
                        }
                    });*/

                    output.close();
                    out.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

}