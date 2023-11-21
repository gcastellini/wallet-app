package com.example.wallet.models;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExchangeAPI {


    public interface ExchangeCallback {
        void onExchangeInfoReady(boolean dataIsAvailable,ExchangeInfo info);
    }

    public interface BitcoinCallback {
        void onExchangeInfoReady(boolean dataIsAvailable,BitcoinInfo info);
    }

    public static void getExchangeInfo(Activity context,ExchangeCallback callback)  {
        String url = "https://api.bluelytics.com.ar/v2/latest";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();

            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    processExchangeResponse(response, context, callback);
                } catch (IOException e) {
                    sendErrorExchange(context, callback, "Internal Error");
                }
            }

        });



    }


    private static void processExchangeResponse(Response response, Activity context, ExchangeCallback callback) throws IOException {
        String output = "";
        String resp=response.body().string();
        try {
            JSONObject responseBody = new JSONObject(resp);
            JSONObject official = responseBody.getJSONObject("oficial");
            Double official_avg = official.getDouble("value_avg");
            JSONObject blue = responseBody.getJSONObject("blue");
            Double blue_avg = blue.getDouble("value_avg");
            Log.d("resp",blue_avg.toString());
            ExchangeInfo info = new ExchangeInfo();
            info.setOfficial_avg(official_avg);
            info.setBlue_avg(blue_avg);
            context.runOnUiThread(() -> callback.onExchangeInfoReady(true, info));
        }catch (JSONException e){
            output = "Error reading data";
            sendErrorExchange(context, callback, output);
        }
    }


    public static void getBitcoinInfo(Activity context,BitcoinCallback callback){
        String url = "https://cex.io/api/tickers/BTC/USD";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();

            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    processBitcoinResponse(response,context,callback);
                } catch (IOException e) {
                    sendErrorBitcoin(context, callback, "Internal Error");
                }
            }
        });



    }
    private static void processBitcoinResponse(Response response, Activity context, BitcoinCallback callback) throws IOException {
        String output = "";
        String resp=response.body().string();
        try {
            JSONObject responseBody = new JSONObject(resp);
            JSONArray bitcoin =responseBody.getJSONArray("data");
            JSONObject firstElement = bitcoin.getJSONObject(0);
            Double btcValue=firstElement.getDouble("last");
            BitcoinInfo info = new BitcoinInfo();
            info.setBtcValue(btcValue);
            context.runOnUiThread(() -> callback.onExchangeInfoReady(true, info));
        }catch (JSONException e){
            output = "Error reading data";
            sendErrorBitcoin(context, callback, output);
        }
    }


    private static void sendErrorExchange(Activity context, ExchangeCallback callback, String output) {
        Log.e("LocationError", output);
        context.runOnUiThread(() -> callback.onExchangeInfoReady(false, null));
    }

    private static void sendErrorBitcoin(Activity context, BitcoinCallback callback, String output) {
        Log.e("LocationError", output);
        context.runOnUiThread(() -> callback.onExchangeInfoReady(false, null));
    }
}

