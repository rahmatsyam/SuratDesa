package com.example.android.suratdesa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.suratdesa.R;

import java.util.ArrayList;


/**
 * Created by user on 18/1/2018.
 */

public class CustomAdapter extends ArrayAdapter {

    private ArrayList<Item> itemList = new ArrayList<>();


    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        itemList = objects;


    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        v = inflater.inflate(R.layout.layout_item, null);
        TextView textView = v.findViewById(R.id.txt_item);
        ImageView imageView = v.findViewById(R.id.icon_item);
        textView.setText(itemList.get(position).getitemName());
        imageView.setImageResource(itemList.get(position).getitemIcon());
        return v;
    }


}
