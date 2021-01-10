package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ezyfood.model.Order;
import com.example.ezyfood.sharedpref.SaldoPref;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {

    TextView drink, price;

    Button ordermore , confirm, myorder;

    private String drinks, prices;

    private EditText qty;

    private List<Order> order = new ArrayList<Order>();
    private int quantity , drinkPrice;
    private String drinkType;
    private SaldoPref saldoPref;
    private TextView saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        drink       = findViewById(R.id.drink);
        price       = findViewById(R.id.price);
        ordermore   = findViewById(R.id.ordermore);
        confirm     = findViewById(R.id.confirm);
        qty         = findViewById(R.id.qty);
        myorder     = findViewById(R.id.myorder);
        saldo       = findViewById(R.id.saldo);
        saldoPref   = new SaldoPref(getBaseContext());
        saldo.setText(Integer.toString(saldoPref.getSaldo()));
        drinkType = getIntent().getExtras().getString("drink");
        drinkPrice = getIntent().getExtras().getInt("price");
        drink.setText(drinkType);
        price.setText("Rp. " + drinkPrice);
        try {
            checkCached();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ordermore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , MainActivity.class);
                intent.putExtra("type" , "order");
                startActivity(intent);
                finish();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = false;
                quantity = Integer.parseInt(qty.getText().toString());
                for (Order drinktype: order) {
                    if(drinktype.getFoodName().equals(drinkType)){
                        drinktype.setQuantity(quantity);
                        drinktype.setPrice(quantity*drinkPrice);
                        flag = true;
                    }
                }
                if(!flag){
                    Log.e("order" , "neworder");
                    Order orders = new Order(quantity , drinkPrice*quantity , drinkType);
                    order.add(orders);
                }
                createCachedFile(getBaseContext(), "order" , order);
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

    public static void createCachedFile(Context context, String key, List<Order> fileName) {
        try{
            if(fileName.size() == 0){
                FileOutputStream fos = context.openFileOutput (key, Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream (fos);
                oos.writeObject(fileName);
                oos.close ();
                fos.close ();
            }else {
                for (Order file : fileName) {
                    Log.e("Food Name " , file.getFoodName());
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
    private void checkCached() throws IOException, ClassNotFoundException {
        List<Order> apkCacheList = (ArrayList<Order>)readCachedFile  (Objects.requireNonNull(getBaseContext()), "order");
        if(apkCacheList == null) {
        } else {
            order = apkCacheList;
            for (Order cacheorder: order) {
                if(cacheorder.getFoodName().equals(drinkType)){
                    qty.setText(Integer.toString(cacheorder.getQuantity()));
                }
            }
        }
    }
    public static Object readCachedFile (Context context, String key) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput (key);
        ObjectInputStream ois = new ObjectInputStream (fis);
        return ois.readObject ();
    }
}