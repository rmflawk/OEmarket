package com.youngstudio.oemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    MainAdapter adapter;


    FragmentManager fragmentManager= getSupportFragmentManager();
    Page1Fragment page1Fragment= new Page1Fragment();
    Page2Fragment page2Fragment= new Page2Fragment();
    Page3Fragment page3Fragment= new Page3Fragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //액션바에 제목이 자동표시 되지 않도록
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        pager= findViewById(R.id.pager);
        adapter= new MainAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.relativelayout,page1Fragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView= findViewById(R.id.bnv);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());



    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction= fragmentManager.beginTransaction();
            switch (menuItem.getItemId()){
                case R.id.bnv_aa:
                    transaction.replace(R.id.relativelayout,page1Fragment).commitAllowingStateLoss();
                    break;
                case R.id.bnv_bb:
                    transaction.replace(R.id.relativelayout,page2Fragment).commitAllowingStateLoss();
                    break;
                case R.id.bnv_cc:
                    transaction.replace(R.id.relativelayout,page3Fragment).commitAllowingStateLoss();
                    break;

            }
            return true;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.option, menu);
//        return true;
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.option, menu);
//        return true;
//    }


}























