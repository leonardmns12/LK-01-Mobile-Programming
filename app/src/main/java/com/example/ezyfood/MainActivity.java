package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView drink, food , snack , topup;

    Button MyOrder , transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drink   = findViewById(R.id.drink);
        food    = findViewById(R.id.food);
        snack   = findViewById(R.id.snack);
        MyOrder = findViewById(R.id.myorder);
        topup   = findViewById(R.id.topup);
        transaction = findViewById(R.id.transcation);

        getSupportActionBar().hide();
        if(getIntent().getExtras().getString("type").equals("map")) {
            String restaurantName = getIntent().getExtras().getString("restaurant").toString();
            Toast.makeText(getBaseContext() , "Kamu memilih restoran " +restaurantName , Toast.LENGTH_LONG).show();
        }
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , drink.class);
                startActivity(intent);
            }
        });
        MyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , MyOrder.class);
                startActivity(intent);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Food.class);
                startActivity(intent);
            }
        });
        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Snack.class);
                startActivity(intent);
            }
        });
        topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TopUp.class);
                startActivity(intent);
            }
        });
        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TransactionActivity.class);
                startActivity(intent);
            }
        });
    }
}