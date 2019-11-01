package com.youngstudio.oemarket;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MainAdapter extends FragmentPagerAdapter {

    Fragment[] fragments= new Fragment[3];

    Context context;
    List<Fragment> listFragment;


//    public MainAdapter(FragmentManager fm, Context context, List<Fragment> listFragment) {
//
//        super(fm);
//
//        this.context = context;
//        this.listFragment = listFragment;
//
//
//        fragments[0]= new Page1Fragment();
//        fragments[1]= new Page2Fragment();
//        fragments[2]= new Page3Fragment();
//    }

    public MainAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        fragments[0]= new Page1Fragment();
        fragments[1]= new Page2Fragment();
        fragments[2]= new Page3Fragment();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        //return  listFragment.get(position);
        return fragments[position];
    }

    @Override
    public int getCount() {

        //return  listFragment.size();
        return fragments.length;
    }




}
