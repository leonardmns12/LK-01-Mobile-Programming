package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ezyfood.MyOrder;
import com.example.ezyfood.OrderActivity;
import com.example.ezyfood.R;

public class Snack extends AppCompatActivity {

    CardView icecream , pudding, burger , coklat;

    Button myorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);
        icecream      = findViewById(R.id.icecream);
        pudding       = findViewById(R.id.pudding);
        burger         = findViewById(R.id.burger);
        coklat      = findViewById(R.id.coklat);
        myorder         = findViewById(R.id.myorder);
        Intent intent = new Intent(getBaseContext() , OrderActivity.class);
        icecream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Ice Cream");
                intent.putExtra("price" , 10000);
                startActivity(intent);
            }
        });
        pudding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Pudding");
                intent.putExtra("price" , 7000);
                startActivity(intent);
            }
        });
        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Burger");
                intent.putExtra("price" , 20000);
                startActivity(intent);
            }
        });
        coklat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Coklat");
                intent.putExtra("price" , 20000);
                startActivity(intent);
            }
        });
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , MyOrder.class);
                startActivity(intent);
            }
        });
    }
}