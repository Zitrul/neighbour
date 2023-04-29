package com.example.samsung.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.samsung.myapp.adapter.JobsAdapter;
import com.example.samsung.myapp.adapter.MyRecyclerViewAdapter;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;

public class List_Fragment extends Fragment {
    //Активность со списками новых работ и т.п
    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);
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

        RecyclerView recyclerView = rootView.findViewById(R.id.rvJobsNew);
        //LinearLayoutManager horizontalLayoutManager
        //        = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.HORIZONTAL, false);
        //recyclerView.setLayoutManager(horizontalLayoutManager);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(mContext, viewColors, animalNames);
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


        RecyclerView recViewJobs= rootView.findViewById(R.id.rvJobs);
        //LinearLayoutManager JobsManager
        //        = new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false);
        //recViewJobs.setLayoutManager(JobsManager);
        JobsAdapter adapterjobs = new JobsAdapter(mContext, viewJobs, nameJobscolmn1,nameJobscolmn2,nameJobscolmn3);
        recViewJobs.setAdapter(adapterjobs);
        return rootView;
    }

}