package com.example.wallet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.wallet.R;
import com.example.wallet.adapters.MyPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeActivity extends AppCompatActivity {

    private final String [] data ={"Dashboards","Wallets","Info"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(
                new MyPagerAdapter(this)
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



        }
}