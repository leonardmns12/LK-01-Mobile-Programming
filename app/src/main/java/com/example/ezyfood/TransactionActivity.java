package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ezyfood.adapter.TransactionAdapter;
import com.example.ezyfood.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionActivity extends AppCompatActivity {

    private TransactionAdapter transactionAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        recyclerView = findViewById(R.id.transcationRecycler);
        try {
            checkCached();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void checkCached() throws IOException, ClassNotFoundException {
        List<Transaction> apkCacheList = (ArrayList<Transaction>)OrderActivity.readCachedFile(Objects.requireNonNull(getBaseContext()), "transaction");
        if(apkCacheList == null) {
            Toast.makeText(this , "cache null" , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this , "cache not null" , Toast.LENGTH_SHORT).show();
            transactionAdapter = new TransactionAdapter(apkCacheList, getBaseContext());
            recyclerView.setAdapter(transactionAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }


}