package com.youngstudio.oemarket;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MainAdapter extends FragmentPagerAdapter {

    Fragment[] fragments= new Fragment[5];


    public MainAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        fragments[0]= new Page1Fragment();
        fragments[1]= new Page2Fragment();
        fragments[2]= new Page3Fragment();
        fragments[3]= new Page4Fragment();
        fragments[4]= new Page5Fragment();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }




}
