package com.example.ezyfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezyfood.R;
import com.example.ezyfood.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

    private List<Transaction> transactionList;
    private Context mContext;

    public TransactionAdapter(List<Transaction> trans, Context mContext) {
        this.transactionList = trans;
        this.mContext = mContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item , date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            date = itemView.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public TransactionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.trans_in_list , parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.MyViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm ( dd-MM-yyyy ) ");
        Long timestamp = transactionList.get(position).getDate();
        String date = sdf.format(new Date(timestamp));
        holder.item.setText(transactionList.get(position).getItemName() + " x" +transactionList.get(position).getQty());
        holder.date.setText(date);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}
