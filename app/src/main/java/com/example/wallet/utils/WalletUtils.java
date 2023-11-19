package com.example.wallet.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wallet.models.WalletData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WalletUtils {

    public static List<WalletData> loadWallets(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("WalletPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("walletsList", null);
        Type type = new TypeToken<ArrayList<WalletData>>() {}.getType();
        List<WalletData> wallets = gson.fromJson(json, type);

        if (wallets == null) {
            wallets = new ArrayList<>();
        }

        return wallets;
    }

    public static void saveWallets(Context context, List<WalletData> wallets) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("WalletPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(wallets);
        editor.putString("walletsList", json);
        editor.apply();
    }
}
