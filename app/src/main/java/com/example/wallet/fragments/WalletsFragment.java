package com.example.wallet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wallet.R;

public class WalletsFragment extends Fragment {

    private final String title;
    public WalletsFragment(String title) {
        this.title=title;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_wallets,container,false);
        TextView dashboardTextView = root.findViewById(R.id.wallets);
        dashboardTextView.setText(title);
        return root;
    }
}