package com.example.samsung.myapp.adapter;

import com.example.samsung.myapp.domain.Order;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends JsonAdapter<ArrayList<Order>> {
    private final JsonAdapter<List<Order>> orderListAdapter;

    public OrderListAdapter() {
        this.orderListAdapter = new Moshi.Builder().build().adapter(Types.newParameterizedType(List.class, Order.class));
    }


    @Override
    public ArrayList<Order> fromJson(JsonReader reader) throws IOException {
        List<Order> list = orderListAdapter.fromJson(reader);
        return new ArrayList<>(list);
    }
    @FromJson
    public ArrayList<Order> fromJson(List<Order> orderList) {
        return new ArrayList<>(orderList);
    }

    @ToJson
    public List<Order> ordersToJson(ArrayList<Order> orderList) {
        return new ArrayList<>(orderList);
    }
    @Override
    public void toJson(JsonWriter writer, ArrayList<Order> value) throws IOException {
        orderListAdapter.toJson(writer, value);
    }
}