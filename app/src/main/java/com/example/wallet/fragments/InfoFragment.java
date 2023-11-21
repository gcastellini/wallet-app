package com.example.wallet.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wallet.R;
import com.example.wallet.models.ExchangeAPI;


public class InfoFragment extends Fragment {

    private final String title;

    public InfoFragment(String title) {
        this.title=title;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info,container,false);
        TextView valueOfficial = view.findViewById(R.id.valueOficial);
        TextView valueBlue = view.findViewById(R.id.valueBlue);
        TextView valueBTC = view.findViewById(R.id.valueBTC);
        ExchangeAPI.getExchangeInfo((Activity) getContext(),((dataIsAvailable, info) -> {
            valueOfficial.setText(String.valueOf(info.getOfficial_avg()));
            valueBlue.setText(String.valueOf(info.getBlue_avg()));
        }));
        ExchangeAPI.getBitcoinInfo((Activity) getContext(),(((dataIsAvailable, info) -> {
            valueBTC.setText(String.valueOf(info.getBtcValue()));
        })));


        return view;
    }
}