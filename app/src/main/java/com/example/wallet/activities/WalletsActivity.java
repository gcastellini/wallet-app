package com.example.wallet.activities;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wallet.R;
import com.example.wallet.models.WalletData;
import com.example.wallet.utils.WalletUtils;

import java.util.ArrayList;
import java.util.List;

public class WalletsActivity extends AppCompatActivity {
    WalletData wallet;
    List<WalletData> wallets = new ArrayList<>();

    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallets);

        wallets=WalletUtils.loadWallets(this);

       EditText editTextWalletName = findViewById(R.id.editTextWalletName);
        EditText editTextAmount = findViewById(R.id.editTextAmount);
        Spinner spinnerCurrency = findViewById(R.id.spinnerCurrency);
       Button buttonSave = findViewById(R.id.buttonSave);

        String[] currencies = {"USD", "ARS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCurrency.setAdapter(adapter);




        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String walletName = editTextWalletName.getText().toString();
                String amount = editTextAmount.getText().toString();
                String currency = spinnerCurrency.getSelectedItem().toString();
                wallet = new WalletData(walletName,amount,currency);
                wallets.add(wallet);
                WalletUtils.saveWallets(activity,wallets);
                Intent intent = new Intent(WalletsActivity.this, HomeActivity.class);
                intent.putExtra("walletsList", new ArrayList<>(wallets));
                startActivity(intent);
            }
        });
    }

}

