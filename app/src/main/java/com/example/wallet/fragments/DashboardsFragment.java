package com.example.wallet.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wallet.R;
import com.example.wallet.models.ExchangeAPI;
import com.example.wallet.models.WalletData;

import java.text.DecimalFormat;
import java.util.List;



public class DashboardsFragment extends Fragment {

    List<WalletData> walletDataList;

    String selectedCurrency="ARS (Official)";

    Double officialUSD;
    Double blueUSD;
    public DashboardsFragment(List<WalletData> walletDataList) {
        this.walletDataList=walletDataList;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboards, container, false);

        Spinner spinnerCurrency = view.findViewById(R.id.spinner);

        String[] currencies = {"ARS (Official)", "ARS (Blue)", "USD"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapter);

        TextView number = view.findViewById(R.id.editTextNumber);



        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCurrency = currencies[position];

                if (walletDataList.isEmpty()) {
                    number.setText("0");
                } else {
                    ExchangeAPI.getExchangeInfo((Activity) getContext(), ((dataIsAvailable, info) -> {
                        if (dataIsAvailable && info != null) {
                            officialUSD = info.getOfficial_avg();
                            blueUSD = info.getBlue_avg();
                            String total = updateTotalAmount(view);
                            number.setText(total);
                        }
                    }));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCurrency = currencies[0];
            }

        });

            return view;
        }





    private String updateTotalAmount(View view) {
        DecimalFormat df = new DecimalFormat("0.00");
        double totalAmount = 0;

        for (WalletData wallet : walletDataList) {
            double amount = Double.parseDouble(wallet.getAmount());

            if (selectedCurrency.equals("ARS (Official)") && wallet.getCurrency().equals("USD")) {
                totalAmount += amount * officialUSD;
            } else if (selectedCurrency.equals("ARS (Blue)") && wallet.getCurrency().equals("USD")) {
                totalAmount += amount * blueUSD;
            } else if (selectedCurrency.equals("USD") && wallet.getCurrency().equals("ARS")) {
                totalAmount += amount / blueUSD;
            } else {
                totalAmount += amount;
            }
        }

        return df.format(totalAmount);
    }

}