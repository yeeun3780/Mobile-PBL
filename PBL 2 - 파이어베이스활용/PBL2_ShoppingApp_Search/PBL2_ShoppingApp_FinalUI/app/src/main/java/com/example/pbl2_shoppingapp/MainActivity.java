package com.example.pbl2_shoppingapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    EditText editText;
    Button button;
    RecyclerView mRecyclerView = null ;
    RecyclerImageTextAdapter mAdapter = null;
    RecyclerViewAdapter madapter2 = null;

    ArrayList<SamsungItem> mPhoneList = new ArrayList<SamsungItem>();
    ArrayList<SamsungItem> mAppList = new ArrayList<SamsungItem>();
    ArrayList<SamsungItem> mAllList = new ArrayList<SamsungItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview) ;

        editText = (EditText)findViewById(R.id.edittext);
        editText.addTextChangedListener(this);


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        //mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

/*
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
*/

        addPhoneItem(getDrawable(R.mipmap.ic_launcher),
                "gallercy 3", "$300") ;
        // 두 번째 아이템 추가.
        addPhoneItem(getDrawable(R.mipmap.ic_launcher),
                "gallercy6", "$700") ;
        // 세 번째 아이템 추가.
        addPhoneItem(getDrawable(R.mipmap.ic_launcher),
                "gallercy 폴드", "$1500") ;

        addAppItem(getDrawable(R.mipmap.ic_launcher),
                "Samsung 60인치 TV", "$300") ;
        // 두 번째 아이템 추가.
        addAppItem(getDrawable(R.mipmap.ic_launcher),
                "Samsung 40인치 TV", "$700") ;
        // 세 번째 아이템 추가.
        addAppItem(getDrawable(R.mipmap.ic_launcher),
                "Zipel 10리터", "$1500") ;

        madapter2 = new RecyclerViewAdapter(getApplicationContext(),mAllList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(madapter2);


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
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        madapter2.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}



