package com.example.ezyfood.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.ezyfood.R;
import com.example.ezyfood.model.Order;

import java.io.IOException;

public class MyOrderAdapter extends ArrayAdapter<Order> {
    private ViewHolder holder;
    private ButtonListener mbuttonListener;

    public MyOrderAdapter(Context context, int resource , ButtonListener buttonListener) {
        super(context, resource);
        this.mbuttonListener = buttonListener;
    }
    public class ViewHolder {
//        TextView textView;
//        ImageView folder;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.file_in_list, parent, false);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Button  remove      = convertView.findViewById(R.id.remove);
        TextView drinkView  = convertView.findViewById(R.id.drink);
        TextView priceView  = convertView.findViewById(R.id.price);
        drinkView.setText(getItem(position).getFoodName());
        priceView.setText("Rp. " +Integer.toString(getItem(position).getPrice()));
        mbuttonListener.onTotalChanged(getItem(position).getPrice());

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mbuttonListener.onButtonClick(position);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }

    public interface ButtonListener{
        void onButtonClick(int position) throws IOException;
        void onTotalChanged(int total);
    }
}
