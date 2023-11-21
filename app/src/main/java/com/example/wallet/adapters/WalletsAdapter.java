package com.example.wallet.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wallet.R;
import com.example.wallet.models.WalletData;
import com.example.wallet.utils.WalletUtils;

import org.w3c.dom.Text;

import java.util.List;

public class WalletsAdapter extends RecyclerView.Adapter<WalletsAdapter.WalletViewHolder> {
    private Activity context;

    private List<WalletData> walletList;


    public WalletsAdapter(List<WalletData> walletList, Activity context) {
        this.walletList = walletList;
        this.context=context;
    }

    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet, parent, false);
        return new WalletViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder holder, int position) {
        WalletData wallet = walletList.get(position);
        holder.itemView.setBackgroundColor(0);
        TextView lblWalletName = holder.itemView.findViewById(R.id.walletNameTextView);
        lblWalletName.setText(wallet.getWalletName());
        TextView lblWalletCurrency = holder.itemView.findViewById(R.id.walletCurrencyTextView);
        lblWalletCurrency.setText(wallet.getCurrency());
        TextView lblWalletAmount= holder.itemView.findViewById(R.id.walletAmountTextView);
        lblWalletAmount.setText(wallet.getAmount());
        Button deleteButton = holder.itemView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(view -> {
            deleteWallet(position);
        });

    }

    private void deleteWallet(int position) {
        walletList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
        WalletUtils.saveWallets(context,walletList);
    }

    @Override
    public int getItemCount() {
        return walletList.size();

    }

    public class WalletViewHolder extends RecyclerView.ViewHolder{
        public WalletViewHolder(@NonNull View itemView){
            super(itemView);
        }
        public void bind(String walletName){

        }
    }

}
