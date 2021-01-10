package com.example.ezyfood.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class TransPref {

    SharedPreferences sharedPreferences;
    Context mContext;


    public TransPref(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences("transcation" , mContext.MODE_PRIVATE);
    }

    public void setTransaction() {

    }
}
