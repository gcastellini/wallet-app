package com.example.wallet.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wallet.R;
import com.example.wallet.activities.HomeActivity;
import com.example.wallet.adapters.WalletsAdapter;
import com.example.wallet.models.WalletData;
import com.example.wallet.utils.WalletUtils;

import java.util.ArrayList;
import java.util.List;

public class WalletsFragment extends Fragment {

    private RecyclerView recyclerView;
    private WalletsAdapter adapter;

    private Bundle mData;
    List<WalletData> walletList;



    private final String title;
    public WalletsFragment(String title,Bundle data, List<WalletData> walletDataList) {
        this.title=title;
        this.mData=data;
        this.walletList=walletDataList;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallets,container,false);

        if(!walletList.isEmpty()){
            recyclerView = view.findViewById(R.id.recyclerViewWalletList);
            adapter = new WalletsAdapter(walletList,getActivity());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            TextView noWalletsTextView = view.findViewById(R.id.textView4);
            noWalletsTextView.setVisibility(View.GONE);
        } else {
            TextView noWalletsTextView = view.findViewById(R.id.textView4);
            noWalletsTextView.setVisibility(View.VISIBLE);
        }

        if (mData != null) {
            recyclerView = view.findViewById(R.id.recyclerViewWalletList);
            walletList = (List<WalletData>) mData.get("walletsList");
            adapter = new WalletsAdapter(walletList, getActivity());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        return view;
    }

}