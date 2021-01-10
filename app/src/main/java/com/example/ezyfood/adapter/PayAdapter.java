package com.example.ezyfood.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood.R;
import com.example.ezyfood.model.Order;

import java.util.List;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.MyViewHolder> {
    private Context mContext;
    private List<Order> orders;

    public PayAdapter (List<Order> order , Context mContext) {
        this.orders = order;
        this.mContext = mContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView foodName, foodPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pay_list_layout , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.foodName.setText(orders.get(position).getFoodName());
        holder.foodPrice.setText("Rp. " +Integer.toString(orders.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
