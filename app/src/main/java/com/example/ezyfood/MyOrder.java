package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ezyfood.adapter.MyOrderAdapter;
import com.example.ezyfood.model.Order;
import com.example.ezyfood.sharedpref.SaldoPref;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyOrder extends AppCompatActivity implements MyOrderAdapter.ButtonListener {

    private MyOrderAdapter mOrderAdapter;
    private List<Order> order = new ArrayList<Order>();
    private SaldoPref saldoPref;

    private ListView moreList;
    private Button paynow;
    private EditText total;
    private int balance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        moreList = findViewById(R.id.orderlist);
        paynow  = findViewById(R.id.paynow);
        total = findViewById(R.id.total);
        saldoPref = new SaldoPref(getBaseContext());
        try {
            checkCached();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        moreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long viewId = view.getId();

                Log.e("view id " , Long.toString(viewId));
            }
        });
        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((saldoPref.getSaldo() >= balance && balance > 0)){
                    saldoPref.removeSaldo(balance);
                    Intent intent = new Intent(getBaseContext() , PayNow.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getBaseContext() , "Saldo tidak mencukupi!" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkCached() throws IOException, ClassNotFoundException {
        List<Order> apkCacheList = (ArrayList<Order>)OrderActivity.readCachedFile(Objects.requireNonNull(getBaseContext()), "order");
        if(apkCacheList == null) {
        } else {
            order = apkCacheList;
            mOrderAdapter = new MyOrderAdapter(getBaseContext(), R.layout.file_in_list, this);
            for (Order orders: order) {
                mOrderAdapter.add(orders);
            }
            if (order.size() > 0) {
                mOrderAdapter.notifyDataSetChanged();
                moreList.setAdapter(mOrderAdapter);
            }

        }
    }

    @Override
    public void onButtonClick(int position) throws IOException {
        mOrderAdapter.clear();
        Log.e("Positon" , Integer.toString(position));
        //IF ORDER NULL REMOVE FILE
        if(order.size() == 1) {
           // removeFiled();
            balance-=order.get(0).getPrice();
            order.remove(0);
            total.setText("Rp. " +Integer.toString(balance));
            OrderActivity.createCachedFile(getBaseContext() , "order" , order);
        } else {
            balance-=order.get(position).getPrice();
            order.remove(position);
            total.setText("Rp. " +Integer.toString(balance));
            mOrderAdapter.addAll(order);
            mOrderAdapter.notifyDataSetChanged();
            moreList.setAdapter(mOrderAdapter);
            OrderActivity.createCachedFile(getBaseContext() , "order" , order);
            try{
                checkCached();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTotalChanged(int total) {
        balance+=total;
        this.total.setText("Rp. " +Integer.toString(balance));
    }
}