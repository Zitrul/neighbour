package com.example.samsung.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ScrollView;

import com.example.samsung.myapp.adapter.JobsAdapter;
import com.example.samsung.myapp.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    //Активность со списками новых работ и т.п
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<Integer> viewColors = new ArrayList<>();
        viewColors.add(R.drawable.flag_russia);
        viewColors.add(R.drawable.flag_russia);
        viewColors.add(R.drawable.flag_russia);
        viewColors.add(R.drawable.flag_russia);
        viewColors.add(R.drawable.flag_russia);
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        RecyclerView recyclerView = findViewById(R.id.rvJobsNew);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, viewColors, animalNames);
        recyclerView.setAdapter(adapter);

        ArrayList<Integer> viewJobs = new ArrayList<>();
        viewJobs.add(R.drawable.flag_russia);
        viewJobs.add(R.drawable.flag_russia);
        viewJobs.add(R.drawable.flag_russia);
        viewJobs.add(R.drawable.flag_russia);
        viewJobs.add(R.drawable.flag_russia);
        viewJobs.add(R.drawable.flag_russia);
        ArrayList<String> nameJobs = new ArrayList<>();
        nameJobs.add("Horse");
        nameJobs.add("Cow");
        nameJobs.add("Camel");
        nameJobs.add("Horse");
        nameJobs.add("Cow");
        nameJobs.add("Camel");


        RecyclerView recViewJobs= findViewById(R.id.rvJobs);
        LinearLayoutManager JobsManager
                = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(JobsManager);
        JobsAdapter adapterjobs = new JobsAdapter(this, viewJobs, nameJobs);
        recyclerView.setAdapter(adapterjobs);
    }
}