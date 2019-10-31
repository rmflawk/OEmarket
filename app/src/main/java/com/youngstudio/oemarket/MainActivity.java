package com.youngstudio.oemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    BottomNavigationView bnv;
    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<Item> datas= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //액션바에 제목이 자동표시 되지 않도록
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        datas.add(new Item("망가진 우산 삽니다", "행당동 ~ 9월 18일","10,000원",R.drawable.one_ace));
        datas.add(new Item("삼성 c타입 충전기 케이블", "중구 약수동 ~ 3일전","무료나눔",R.drawable.bg_one01));
        datas.add(new Item("100만원으로 본체 2개삽니다", "성수1가제2동 ~ 7일전","115,000원",R.drawable.bg_one02));
        datas.add(new Item("커피머신기 패키지로 삽니다", "황학동 ~ 10월8일","",R.drawable.bg_one03));
        datas.add(new Item("버즈 화이트 삽니다", "금호동4가 ~ 9월26일","40,000원",R.drawable.bg_one04));
        datas.add(new Item("서울 왕십리 버즈 실버 삽니다", "용두동 ~ 9월25일","250,000원",R.drawable.bg_one05));
        datas.add(new Item("온누리 상품권 삽니다", "동대문구 전농동 ~ 9월24일","",R.drawable.bg_one06));
        datas.add(new Item("모니터 어댑터 사요", "왕십리동 ~ 9월 20일","180,000원",R.drawable.bg_one07));
        datas.add(new Item("망가진 우산 삽니다", "행당동 ~ 9월 18일","10,000원",R.drawable.bg_one08));



        recyclerView= findViewById(R.id.recycler);
        adapter= new MyAdapter(datas, this);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰 구분선 추가
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        bnv= findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //원래는 보통 아이템을 클릭할 때마다
                //Fragment는 변경하는 것이 일반적임.
                switch ( item.getItemId() ){
                    case R.id.bnv_aa:
                        Toast.makeText(MainActivity.this, "aa", Toast.LENGTH_SHORT).show();
                        //findViewById(R.id.recycler).setBackgroundColor(Color.CYAN);
                        break;
                    case R.id.bnv_bb:
                        Toast.makeText(MainActivity.this, "bb", Toast.LENGTH_SHORT).show();
                        //findViewById(R.id.recycler).setBackgroundColor(Color.MAGENTA);
                        break;
                    case R.id.bnv_cc:
                        Toast.makeText(MainActivity.this, "cc", Toast.LENGTH_SHORT).show();
                        //findViewById(R.id.recycler).setBackgroundColor(Color.GREEN);
                        break;
                    case R.id.bnv_dd:
                        Toast.makeText(MainActivity.this, "dd", Toast.LENGTH_SHORT).show();
                        //findViewById(R.id.recycler).setBackgroundColor(Color.BLACK);
                        break;
                }
                //리턴값이 true가 아니면 동작하지 않음
                return true;
            }
        });


    }//on Create


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }


}






















