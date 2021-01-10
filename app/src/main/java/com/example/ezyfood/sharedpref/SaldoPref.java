package com.example.ezyfood.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class SaldoPref {

    Context mContext;
    SharedPreferences sharedPreferences;

    public SaldoPref(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences("saldo" , Context.MODE_PRIVATE);
    }

    public int getSaldo() {
        return sharedPreferences.getInt("saldo" , 0);
    }

    public void removeSaldo(int saldo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("saldo" , sharedPreferences.getInt("saldo" , 0) - saldo);
        editor.apply();
    }

    public void setSaldo(int saldo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("saldo" , sharedPreferences.getInt("saldo" , 0) + saldo);
        editor.apply();
    }
}
