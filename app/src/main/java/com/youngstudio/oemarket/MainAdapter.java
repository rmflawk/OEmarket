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
        fragments[0]= new MainFragment();
        fragments[1]= new KategorieFragment();
        fragments[2]= new WritingFragment();
        fragments[3]= new ChattingFragment();
        fragments[4]= new MyPageFragment();
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
