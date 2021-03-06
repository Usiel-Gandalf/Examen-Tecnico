package com.example.examentecnico.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examentecnico.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private final Context context;
    private final Integer layout;
    private List<UserInfo> listInfo;

    public MyAdapter(Context context, Integer layout, List<UserInfo> listInfo){
        this.context = context;
        this.layout = layout;
        this.listInfo = listInfo;
    }
    @Override
    public int getCount() {
        return this.listInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView user, country, state, gender;
        ImageView imageList;
        UserInfo us = listInfo.get(position);
        if (view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_info, null);
        }
        user = view.findViewById(R.id.textViewUser);
        country = view.findViewById(R.id.textViewCountry);
        state = view.findViewById(R.id.textViewState);
        gender = view.findViewById(R.id.textViewGender);
        imageList = view.findViewById(R.id.imageViewItemsList);

        user.setText("Usuario: " + us.user);
        country.setText("Pais: " + us.country);
        state.setText("Estado: " + us.state);
        gender.setText("Genero: " + us.gender);
        imageList.setImageResource(R.mipmap.icono);
        return view;
    }
}
