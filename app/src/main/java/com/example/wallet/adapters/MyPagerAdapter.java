package com.example.wallet.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wallet.fragments.DashboardsFragment;
import com.example.wallet.fragments.InfoFragment;
import com.example.wallet.fragments.WalletsFragment;

public class MyPagerAdapter extends FragmentStateAdapter {
    private final String [] data ={"Dashboards","Wallets","Info"};

    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public MyPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public MyPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                return new DashboardsFragment(data[position]);
            case 1:
                return new WalletsFragment(data[position]);
            case 2:
                return new InfoFragment(data[position]);
            default:
                return new DashboardsFragment(data[position]);
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
