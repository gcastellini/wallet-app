package com.example.wallet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wallet.R;


public class DashboardsFragment extends Fragment {

    private final String title;
    public DashboardsFragment(String title) {
        this.title=title;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboards,container,false);
        TextView dashboardTextView = root.findViewById(R.id.dashboards);
        dashboardTextView.setText(title);
        return root;
    }
}