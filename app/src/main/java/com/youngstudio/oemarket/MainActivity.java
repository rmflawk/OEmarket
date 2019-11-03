package com.youngstudio.oemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    MainAdapter adapter;

    List<Fragment> listFragment;
    BottomNavigationView navigationView;


    FragmentManager fragmentManager = getSupportFragmentManager();
    Page1Fragment page1Fragment = new Page1Fragment();
    Page2Fragment page2Fragment = new Page2Fragment();
    Page3Fragment page3Fragment = new Page3Fragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //액션바에 제목이 자동표시 되지 않도록
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //initView();



        //FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.replace(R.id.relativelayout, page1Fragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bnv);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        pager = findViewById(R.id.pager);
        adapter = new MainAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);




    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.bnv_aa:
//                    Intent intent= new Intent(getApplicationContext(),Page1Fragment.class);
//                    startActivity(intent);
//                    finish();
                    transaction.replace(R.id.relativelayout, page1Fragment).commitAllowingStateLoss();
                    break;
                case R.id.bnv_bb:
//                    Intent intent2= new Intent(getApplicationContext(),Page2Fragment.class);
//                    startActivity(intent2);
//                    finish();
                    transaction.replace(R.id.relativelayout, page2Fragment).commitAllowingStateLoss();
                    break;
                case R.id.bnv_cc:
//                    Intent intent3= new Intent(getApplicationContext(),Page3Fragment.class);
//                    startActivity(intent3);
//                    finish();
                    transaction.replace(R.id.relativelayout, page3Fragment).commitAllowingStateLoss();
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

    //        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                navigationView.getMenu().getItem(position).setChecked(true);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


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



//    public void initView(){
//        pager= (ViewPager)findViewById(R.id.pager);
//        navigationView= (BottomNavigationView)findViewById(R.id.bnv);
//
//        listFragment= new ArrayList<>();
//        listFragment.add(new Page1Fragment());
//        listFragment.add(new Page2Fragment());
//        listFragment.add(new Page3Fragment());
//        adapter= new MainAdapter(getSupportFragmentManager(),this,listFragment);
//        pager.setAdapter(adapter);
//
//        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()){
//                    case R.id.bnv_aa:
//                        pager.setCurrentItem(0);
//                        return  true;
//                    case R.id.bnv_bb:
//                        pager.setCurrentItem(1);
//                        return  true;
//                    case R.id.bnv_cc:
//                        pager.setCurrentItem(2);
//                        return  true;
//                    default:
//                        break;
//                }
//
//                return false;
//            }
//        });
//
//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                navigationView.getMenu().getItem(position).setChecked(true);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//    }





















