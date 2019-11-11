package com.example.pbl2_shoppingapp;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView = null ;
    RecyclerImageTextAdapter mAdapter = null ;
    ArrayList<SamsungItem> mPhoneList = new ArrayList<SamsungItem>();
    ArrayList<SamsungItem> mAppList = new ArrayList<SamsungItem>();
    ArrayList<SamsungItem> mAllList = new ArrayList<SamsungItem>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_main_list) ;

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        //mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

/*
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
*/

        addPhoneItem(getDrawable(R.mipmap.ic_launcher),
                "갤럭시 3", "$300") ;
        // 두 번째 아이템 추가.
        addPhoneItem(getDrawable(R.mipmap.ic_launcher),
                "갤럭시 6", "$700") ;
        // 세 번째 아이템 추가.
        addPhoneItem(getDrawable(R.mipmap.ic_launcher),
                "갤럭시 폴드", "$1500") ;

        addAppItem(getDrawable(R.mipmap.ic_launcher),
                "삼성 60인치 TV", "$300") ;
        // 두 번째 아이템 추가.
        addAppItem(getDrawable(R.mipmap.ic_launcher),
                "삼성 40인치 TV", "$700") ;
        // 세 번째 아이템 추가.
        addAppItem(getDrawable(R.mipmap.ic_launcher),
                "지펠 10리터", "$1500") ;


    }

    public void onViewAllClick(View view) {

        mAdapter = new RecyclerImageTextAdapter(mAllList) ;

        mAdapter.notifyDataSetChanged() ;
        mRecyclerView.setAdapter(mAdapter) ;
    }


    public void onViewPhoneClick(View view) {

        mAdapter = new RecyclerImageTextAdapter(mPhoneList) ;

        mAdapter.notifyDataSetChanged() ;
        mRecyclerView.setAdapter(mAdapter) ;
    }


    public void onViewAppClick(View view) {
        mAdapter = new RecyclerImageTextAdapter(mAppList) ;

        mAdapter.notifyDataSetChanged() ;
        mRecyclerView.setAdapter(mAdapter) ;
    }

    public void addPhoneItem(Drawable icon, String title, String desc) {
        SamsungItem item = new SamsungItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        mPhoneList.add(item);
        mAllList.add(item);
    }

    public void addAppItem(Drawable icon, String title, String desc) {
        SamsungItem item = new SamsungItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        mAppList.add(item);
        mAllList.add(item);

    }


    }

