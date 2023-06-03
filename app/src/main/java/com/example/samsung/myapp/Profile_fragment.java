package com.example.samsung.myapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsung.myapp.adapter.JobsAdapter;
import com.example.samsung.myapp.adapter.MyAdapter;
import com.example.samsung.myapp.adapter.MyRecyclerViewAdapter;
import com.example.samsung.myapp.adapter.MyWorkAdapter;
import com.example.samsung.myapp.dialogs.MyWorkDialog;
import com.example.samsung.myapp.dialogs.WorkerDialog;
import com.example.samsung.myapp.domain.Login;
import com.example.samsung.myapp.domain.Order;
import com.example.samsung.myapp.domain.UserInfo;
import com.example.samsung.myapp.rest.LoginApiService;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_fragment extends Fragment {
    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //MapKitFactory.setApiKey(MAPKIT_API_KEY);
        //MapKitFactory.initialize(this);


        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        //Получение информации о вас

        LoginApiService.getInstance().geUserInfo(MainActivity.auth_id).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                Log.d(MainActivity.REST, response.body().getEmail());
                TextView name = rootView.findViewById(R.id.textViewName);
                name.setText(response.body().getLogin());
                TextView phone = rootView.findViewById(R.id.textViewPhone);
                phone.setText(response.body().getPhone());
                TextView tg = rootView.findViewById(R.id.textViewTG3);
                tg.setText(response.body().getTg());


            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.d(MainActivity.REST, t.getMessage());
            }
        });

        //Получение того что выполняет пользователь

        LoginApiService.getInstance().getAllmarket(MainActivity.auth_id).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                Log.d("REST",response.body().toString());
                System.out.println(response.body().size());
                ArrayList<Order> arr = new ArrayList<Order>();

                for (int i = 0; i < response.body().size(); i ++){
                    arr.add(response.body().get(i));
                }
                System.out.println(arr.size());
                RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
                LinearLayoutManager horizontalLayoutManager
                      = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(horizontalLayoutManager);
                MyAdapter adapter = new MyAdapter(getContext(), arr);
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(new MyAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Order o = arr.get(position);
                        MyWorkDialog myWorkDialog = MyWorkDialog.newInstance(o);

                        myWorkDialog.show(getFragmentManager(), "spot_fragment");
                        System.out.println(position);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("REST",t.getMessage());
            }
        });;


        LoginApiService.getInstance().getMyWorks(MainActivity.auth_id).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                Log.d("REST",response.body().toString());
                System.out.println(response.body().size());
                ArrayList<Order> arr = new ArrayList<Order>();

                for (int i = 0; i < response.body().size(); i ++){
                    arr.add(response.body().get(i));
                }
                System.out.println(arr.size());
                RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView1);
                LinearLayoutManager horizontalLayoutManager
                        = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(horizontalLayoutManager);
                MyWorkAdapter adapter = new MyWorkAdapter(getContext(), arr);
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(new MyWorkAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Order o = arr.get(position);
                        MyWorkDialog myWorkDialog = MyWorkDialog.newInstance(o);

                        myWorkDialog.show(getFragmentManager(), "spot_fragment");
                        System.out.println(position);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("REST",t.getMessage());
            }
        });;

        //Получение откликов на заказ

        LoginApiService.getInstance().checkmepls(MainActivity.auth_id).enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                Log.d("REST","OK");
                for(int i = 0; i < response.body().size(); i++){
                    WorkerDialog myDialogFragment = WorkerDialog.newInstance(response.body().get(0).getTg(), response.body().get(0).getPhone());

                    //myDialogFragment.show(manager, "dialog");

                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    //myDialogFragment.show(manager, "dialog");

                    FragmentTransaction transaction = manager.beginTransaction();
                    myDialogFragment.show(transaction, "dialog");

                }
                //System.out.println(response.body().size());
            }

            @Override
            public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                Log.d("REST",t.getMessage());
            }
        });;
        return rootView;
    }
}
