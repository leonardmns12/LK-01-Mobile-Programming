package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ezyfood.R;
import com.example.ezyfood.adapter.MyOrderAdapter;
import com.example.ezyfood.adapter.PayAdapter;
import com.example.ezyfood.adapter.TransactionAdapter;
import com.example.ezyfood.model.Order;
import com.example.ezyfood.model.Transaction;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PayNow extends AppCompatActivity {

    private List<Order> order;
    private RecyclerView payList;
    private List<Transaction> transactions = new ArrayList<>();
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_now);
        payList     = findViewById(R.id.paylist);
        home        = findViewById(R.id.home);
        try {
            checkCached();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , MainActivity.class);
                intent.putExtra("type" , "paynow");
                startActivity(intent);
            }
        });
    }

    private void checkCached() throws IOException, ClassNotFoundException {
        List<Order> apkCacheList = (ArrayList<Order>)OrderActivity.readCachedFile(Objects.requireNonNull(getBaseContext()), "order");
        if(apkCacheList == null) {
        } else {
            order = apkCacheList;
            PayAdapter payAdapter = new PayAdapter(apkCacheList , this);
            payList.setAdapter(payAdapter);
            payList.setLayoutManager(new LinearLayoutManager(this));
            addTransaction(apkCacheList);
            removeFiled();
        }
    }


    private void removeFiled() throws IOException {
        try{
            FileOutputStream fos = openFileOutput ("order", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.write(null);
            oos.close();
            fos.close ();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void createCachedFile(Context context, String key, List<Transaction> fileName) {
        try{
            if(fileName.size() == 0){
                FileOutputStream fos = context.openFileOutput (key, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream (fos);
                oos.writeObject(fileName);
                oos.close ();
                fos.close ();
            }else {
                for (Transaction file : fileName) {
                    Log.e("Food Name " , file.getItemName());
                    FileOutputStream fos = context.openFileOutput (key, Context.MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream (fos);
                    oos.writeObject(fileName);
                    oos.close ();
                    fos.close ();
                }
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    private void checkTransaction()  {
        try{
            List<Transaction> apkCacheList = (ArrayList<Transaction>)OrderActivity.readCachedFile(Objects.requireNonNull(getBaseContext()), "transaction");
            if(apkCacheList != null)transactions = apkCacheList;
        }catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addTransaction(List<Order> order) throws IOException, ClassNotFoundException {
        checkTransaction();
        for (Order orders: order) {
            transactions.add(new Transaction(orders.getFoodName(), orders.getQuantity()));
        }
        createCachedFile(getBaseContext() , "transaction" , transactions);
    }
}
