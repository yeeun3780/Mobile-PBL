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

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView = null ;
    RecyclerImageTextAdapter mAdapter = null ;
    ArrayList<SamsungItem> mList = new ArrayList<SamsungItem>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_main_list) ;

        mAdapter = new RecyclerImageTextAdapter(mList) ;
        mRecyclerView.setAdapter(mAdapter) ;



        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        //mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)) ;


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        addItem(getDrawable(R.mipmap.ic_launcher),
                "갤럭시 3", "$300") ;
        // 두 번째 아이템 추가.
        addItem(getDrawable(R.mipmap.ic_launcher),
                "갤럭시 6", "$700") ;
        // 세 번째 아이템 추가.
        addItem(getDrawable(R.mipmap.ic_launcher),
                "갤럭시 폴드", "$1500") ;

        mAdapter.notifyDataSetChanged() ;
    }

    public void addItem(Drawable icon, String title, String desc) {
        SamsungItem item = new SamsungItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        mList.add(item);
    }


    }

