package com.example.wallet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.wallet.R;
import com.example.wallet.adapters.MyPagerAdapter;
import com.example.wallet.models.WalletData;
import com.example.wallet.utils.WalletUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private final String [] data ={"Dashboards","Wallets","Info"};

    private List<WalletData> wallets;
    private MenuItem add_wallet_item;

    private MenuItem logout_item;





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.list,menu);
        add_wallet_item = menu.findItem(R.id.add_wallet);
        Log.d("wallet",String.valueOf(add_wallet_item.getItemId()));
        logout_item = menu.findItem(R.id.logout);
        Log.d("logout",String.valueOf(logout_item.getItemId()));
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case 2131230792:
                Intent intent = new Intent(this, WalletsActivity.class);
                startActivity(intent);
                return true;
            case 2131230972:
                Intent logoutIntent = new Intent(this, LoginActivity.class);
                startActivity(logoutIntent);
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        wallets= WalletUtils.loadWallets(this);

        Bundle intent = getIntent().getExtras();


        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(
                new MyPagerAdapter(this,intent,wallets)
        );



        TabLayout tabsLayout= findViewById(R.id.tab_layout);
        new TabLayoutMediator(
                tabsLayout,
                viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(data[position]);
                        tab.setIcon(R.drawable.ic_launcher_foreground);
                    }
                }

        ).attach();

        tabsLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                add_wallet_item.setVisible(tab.getPosition() == 1);
                logout_item.setVisible(tab.getPosition() == 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                add_wallet_item.setVisible(tab.getPosition() == 1);
                logout_item.setVisible(tab.getPosition() == 0);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });



        }

}