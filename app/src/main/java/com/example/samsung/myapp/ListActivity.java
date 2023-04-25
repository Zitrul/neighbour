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
        ArrayList<String> nameJobscolmn1 = new ArrayList<>();
        ArrayList<String> nameJobscolmn2 = new ArrayList<>();
        ArrayList<String> nameJobscolmn3 = new ArrayList<>();
        nameJobscolmn1.add("Horse");
        nameJobscolmn1.add("Cow");
        nameJobscolmn2.add("Camel");
        nameJobscolmn2.add("Horse");
        nameJobscolmn3.add("Cow");
        nameJobscolmn3.add("Camel");


        RecyclerView recViewJobs= findViewById(R.id.rvJobs);
        LinearLayoutManager JobsManager
                = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false);
        recViewJobs.setLayoutManager(JobsManager);
        JobsAdapter adapterjobs = new JobsAdapter(this, viewJobs, nameJobscolmn1,nameJobscolmn2,nameJobscolmn3);
        recViewJobs.setAdapter(adapterjobs);
    }
}