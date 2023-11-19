package com.example.wallet.models;

import java.io.Serializable;

public class WalletData implements Serializable {

    private String walletName;
    private String amount;
    private String currency;
    public WalletData(String walletName, String amount, String currency) {
        this.walletName = walletName;
        this.amount = amount;
        this.currency = currency;
    }

    public String getWalletName() {
        return walletName;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}

