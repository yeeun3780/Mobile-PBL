package com.example.pbl2_shoppingapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView = null ;
    RecyclerImageTextAdapter mAdapter = null ;
    LinearLayoutManager mLinearLayoutManager;
    ArrayList<SamsungItem> mPhoneList = new ArrayList<SamsungItem>();
    ArrayList<SamsungItem> mAppList = new ArrayList<SamsungItem>();
    ArrayList<SamsungItem> mAllList = new ArrayList<SamsungItem>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_main_list) ;

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new RecyclerImageTextAdapter(this,mAllList) ;
        mRecyclerView.setAdapter(mAdapter);

/*
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

*/
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SamsungItem item = mAllList.get(position);
                Toast.makeText(getApplicationContext(), item.getTitle()+' '+item.getDesc(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

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

        //mAdapter = new RecyclerImageTextAdapter(mAllList) ;
        mAdapter = new RecyclerImageTextAdapter(this,mAllList) ;

        mAdapter.notifyDataSetChanged() ;
        mRecyclerView.setAdapter(mAdapter) ;

    }


    public void onViewPhoneClick(View view) {

        //mAdapter = new RecyclerImageTextAdapter(mPhoneList) ;
        mAdapter = new RecyclerImageTextAdapter(this,mPhoneList) ;

        mAdapter.notifyDataSetChanged() ;
        mRecyclerView.setAdapter(mAdapter) ;
    }


    public void onViewAppClick(View view) {
        //mAdapter = new RecyclerImageTextAdapter(mAppList) ;
        mAdapter = new RecyclerImageTextAdapter(this,mAppList) ;

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

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}

public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private MainActivity.ClickListener clickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}



