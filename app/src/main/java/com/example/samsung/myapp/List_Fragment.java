package com.example.samsung.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.samsung.myapp.adapter.JobsAdapter;
import com.example.samsung.myapp.adapter.MyRecyclerViewAdapter;
import com.example.samsung.myapp.domain.Login;
import com.example.samsung.myapp.domain.Order;
import com.example.samsung.myapp.domain.Spot;
import com.example.samsung.myapp.rest.LoginApiService;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.DialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List_Fragment extends Fragment {
    //Активность со списками новых работ и т.п
    Context mContext;
    public static int last_order_id;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private ArrayList<String> list1 = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


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

        //RecyclerView recyclerView = rootView.findViewById(R.id.rvJobsNew);
        //LinearLayoutManager horizontalLayoutManager
         //       = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        //recyclerView.setLayoutManager(horizontalLayoutManager);
        //MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getContext(), viewColors, animalNames);
        //recyclerView.setAdapter(adapter);

        ArrayList<Integer> viewJobs = new ArrayList<>();
        viewJobs.add(R.drawable.flag_russia);
        viewJobs.add(R.drawable.flag_russia);
        viewJobs.add(R.drawable.flag_russia);
        ArrayList<String> nameJobscolmn1 = new ArrayList<String>();

        ArrayList<String> nameJobscolmn2 = new ArrayList<String>();
        ArrayList<String> nameJobscolmn3 = new ArrayList<String>();
        nameJobscolmn1.add("Horse1");
        nameJobscolmn1.add("Cow1");
        nameJobscolmn1.add("Cow1");
        nameJobscolmn2.add("Camel2");
        nameJobscolmn2.add("Horse2");
        nameJobscolmn2.add("Horse2");
        nameJobscolmn3.add("Cow3");
        nameJobscolmn3.add("Camel3");
        nameJobscolmn3.add("Camel3");



        LoginApiService.getInstance().getOrderwith().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                Log.d(MainActivity.REST, response.body().toString());

                ArrayList<Order> list2d = new ArrayList<Order>();


                for(int i = 0; i < response.body().size(); i ++){
                    Log.d(MainActivity.REST,response.body().get(i).getName());

                    list2d.add(response.body().get(i));
                }
                System.out.println(list2d.toString());
                RecyclerView recViewJobs = rootView.findViewById(R.id.rvJobs);
                GridLayoutManager JobsManager
                        = new GridLayoutManager(getContext(), 3);
                recViewJobs.setLayoutManager(JobsManager);

                JobsAdapter adapterjobs = new JobsAdapter(getContext(), list2d);
                recViewJobs.setAdapter(adapterjobs);
                adapterjobs.setClickListener(new JobsAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Order o = list2d.get(position);
                        OrderTextDialogForList dialogFragment = OrderTextDialogForList.newInstance(o);
                        dialogFragment.show(getFragmentManager(), "spot_fragment");
                        System.out.println(position);
                    }
                });


            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d(MainActivity.REST, t.getMessage());
            }
        });





        Button b = rootView.findViewById(R.id.button_add);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderActivity myDialogFragment = new OrderActivity();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                //myDialogFragment.show(manager, "dialog");

                FragmentTransaction transaction = manager.beginTransaction();
                myDialogFragment.show(transaction, "dialog");

            }
        });

        return rootView;
    }

}