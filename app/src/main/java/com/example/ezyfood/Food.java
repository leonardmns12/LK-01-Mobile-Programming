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

public class Food extends AppCompatActivity {

    CardView pizza , nasigoreng, miekuah , miegoreng;

    Button myorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        pizza      = findViewById(R.id.pizza);
        nasigoreng       = findViewById(R.id.nasigoreng);
        miegoreng         = findViewById(R.id.miegoreng);
        miekuah      = findViewById(R.id.miekuah);
        myorder         = findViewById(R.id.myorder);
        Intent intent = new Intent(getBaseContext() , OrderActivity.class);
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Pizza");
                intent.putExtra("price" , 35000);
                startActivity(intent);
            }
        });
        nasigoreng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Nasi Goreng");
                intent.putExtra("price" , 20000);
                startActivity(intent);
            }
        });
        miegoreng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Mie Goreng");
                intent.putExtra("price" , 12000);
                startActivity(intent);
            }
        });
        miekuah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Mie Kuah");
                intent.putExtra("price" , 12000);
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