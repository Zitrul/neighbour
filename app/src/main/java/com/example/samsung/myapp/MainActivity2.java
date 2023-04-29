package com.example.samsung.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity2 extends AppCompatActivity {
    //Активность для профиля
    private int mode = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Map_Fragment map_fragment = new Map_Fragment();
        List_Fragment list_fragment = new List_Fragment();
        Profile_fragment profile_fragment = new Profile_fragment();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, profile_fragment);
//
        fragmentTransaction.commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_list:
                        System.out.println("Список");
                        mode = 2;
                        // Действия при выборе первого элемента меню
                        return true;
                    case R.id.action_mail:
                        mode = 1;
                        System.out.println("главная");
                        // Действия при выборе второго элемента меню
                        return true;
                    case R.id.action_map:
                        mode = 3;
                        System.out.println("Карта");
                        // Действия при выборе третьего элемента меню
                        return true;
                    case R.id.action_profile:
                        mode = 4;
                        System.out.println("Проф");
                        // Действия при выборе третьего элемента меню
                        return true;
                    default:
                        return false;
                }
            }
        });

        if(mode == 4){

            fragmentTransaction.replace(R.id.fragment_container, profile_fragment);
            fragmentTransaction.commit();
        }
        if (mode == 3){
            fragmentTransaction.replace(R.id.fragment_container,map_fragment);
            fragmentTransaction.commit();
        }
        if (mode == 2){
            fragmentTransaction.replace(R.id.fragment_container, list_fragment);
            fragmentTransaction.commit();
        }
        if(mode == 1){
            fragmentTransaction.replace(R.id.fragment_container, profile_fragment);
            fragmentTransaction.commit();
        }
        //Intent intent = new Intent(this, ListActivity.class);

    }

}