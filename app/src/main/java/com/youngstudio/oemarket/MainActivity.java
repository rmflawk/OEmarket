package com.youngstudio.oemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    MainAdapter adapter;

    public static BottomNavigationView navigationView;
    public static TextView main_tv;
    public static ImageView main_iv;
    public static AppBarLayout appBarLayout;
    public static Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        main_tv= findViewById(R.id.main_tv);
        main_iv= findViewById(R.id.main_iv);
        appBarLayout= findViewById(R.id.appbarlayout);

        navigationView= findViewById(R.id.bnv);

        //액션바에 제목이 자동표시 되지 않도록
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        pager = findViewById(R.id.pager);
        adapter = new MainAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        //FragmentTransaction transaction= fragmentManager.beginTransaction();
        //transaction.replace(R.id.relativelayout,page1Fragment).commitAllowingStateLoss();

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bnv);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                //Toast.makeText(MainActivity.this, position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position)
            {
                //navigationView.getMenu().getItem(position).setChecked(true);
                switch (position) {
                    case 0:
                        MainActivity.main_tv.setText("도선동");
                        MainActivity.main_iv.setVisibility(View.VISIBLE);
                        MainActivity.appBarLayout.setVisibility(View.VISIBLE);
                        navigationView.getMenu().getItem(position).setChecked(true);
                        break;

                    case 1:
                        MainActivity.main_tv.setText("카테고리");
                        MainActivity.main_iv.setVisibility(View.GONE);
                        MainActivity.appBarLayout.setVisibility(View.GONE);
                        navigationView.getMenu().getItem(position).setChecked(true);
                        break;

                    case 2:
                        MainActivity.main_iv.setVisibility(View.GONE);
                        MainActivity.appBarLayout.setVisibility(View.GONE);
                        navigationView.getMenu().getItem(position).setChecked(true);
                        break;
                    case 3:
                        MainActivity.main_iv.setVisibility(View.GONE);
                        MainActivity.appBarLayout.setVisibility(View.GONE);
                        navigationView.getMenu().getItem(position).setChecked(true);
                        break;
//                    case 4:
//                        MainActivity.main_iv.setVisibility(View.GONE);
//                        MainActivity.appBarLayout.setVisibility(View.GONE);
//                        navigationView.getMenu().getItem(position).setChecked(true);
//                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

        //getHashKey();

    }//onCreate



    public void clickMap(View view) {
        Intent intent= new Intent(MainActivity.this, MapActivity.class);
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10){
            if(resultCode == RESULT_OK){
                main_tv.setText(data.getStringExtra("array"));
            }else{
                Toast.makeText(this, "결과값이 없습니다.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            //FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.bnv_aa:
                    MainActivity.main_tv.setText("도선동");
                    MainActivity.main_iv.setVisibility(View.VISIBLE);
                    MainActivity.appBarLayout.setVisibility(View.VISIBLE);
                    //transaction.replace(R.id.relativelayout, page1Fragment).commitAllowingStateLoss();
                    pager.setCurrentItem(0,true);
                    //Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.bnv_bb:
                    MainActivity.main_tv.setText("카테고리");
                    MainActivity.main_iv.setVisibility(View.GONE);
                    MainActivity.appBarLayout.setVisibility(View.GONE);
                    //transaction.replace(R.id.relativelayout, page2Fragment).commitAllowingStateLoss();
                    pager.setCurrentItem(1,true);
                    break;

                case R.id.bnv_cc:
                    MainActivity.main_iv.setVisibility(View.GONE);
                    MainActivity.appBarLayout.setVisibility(View.GONE);
                    pager.setCurrentItem(2,true);
                    break;
                case R.id.bnv_dd:
                    MainActivity.main_iv.setVisibility(View.GONE);
                    MainActivity.appBarLayout.setVisibility(View.GONE);
                    pager.setCurrentItem(3,true);
                    break;
                case R.id.bnv_ee:
                    MainActivity.main_iv.setVisibility(View.GONE);
                    MainActivity.appBarLayout.setVisibility(View.GONE);
                    Intent intent= new Intent(MainActivity.this, WritingActivity.class);
                    startActivity(intent);
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



}//MainActivity.class





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


























