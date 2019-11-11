package com.example.shoppingmall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity { ;
    private DatabaseReference mDatabase;
    private DatabaseReference itemDatabase;
    RecyclerView mRecyclerView = null;
    RecyclerImageTextAdapter mAdapter = null ;
    ArrayList<SamsungItem> mPhoneList = new ArrayList<SamsungItem>();
    ArrayList<SamsungItem> mAppList = new ArrayList<SamsungItem>();
    ArrayList<SamsungItem> mAllList = new ArrayList<SamsungItem>();
    ArrayList<SamsungItem> mSearchList = new ArrayList<SamsungItem>();
    Button searchButtton;
    EditText searchText;
    //SearchItem search = new SearchItem();

    private  void writeProduct(String productId,int product_num, String name, String category,int price ,String product_info){
        //mDatabase.child("product").child(productId).setValue(product);
        Map<String,Object> childUpdates=new HashMap<>();
        Map<String,Object> postValues=null;
        Product product = new Product(product_num,name,category, price ,product_info);
        postValues=product.toMap();
        childUpdates.put("/product/"+productId,postValues);
        mDatabase.updateChildren(childUpdates);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase =  FirebaseDatabase.getInstance().getReference();

        //파이어베이스에서 테이블의 필드와 값이 실시간으로 업데이트 된 것을 볼수 있다.
        writeProduct("product1",1,"LG 밥솥","가전",500000,"Cuckoo");
        writeProduct("product2",2,"LG 노트북","전자",1000000,"Gram");
        writeProduct("product3",3,"Samsung 노트북","전자",1200000,"Always 9");
        writeProduct("product4",1,"Samsung 세탁기","가전",950000,"Bubble");
        writeProduct("product5",1,"Apple 노트북","전자",2000000,"Mac");
        writeProduct("product6",1,"Apple 핸드폰","전자",1100000,"11 pro");

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
        ValueEventListener productListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    //String key=postSnapshot.getKey();
                    Product get=postSnapshot.getValue(Product.class);

                    if(get.category.equals("가전"))
                    addAppItem(getDrawable(R.mipmap.ic_launcher),
                            get.name, String.valueOf(get.price));
                else
                    addPhoneItem(getDrawable(R.mipmap.ic_launcher),
                            get.name, String.valueOf(get.price)) ;
            }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "localPost:onCancelled", databaseError.toException());
            }
        };
        Query sortbyProduct=FirebaseDatabase.getInstance().getReference().child("product").orderByChild("productId");
        sortbyProduct.addListenerForSingleValueEvent(productListener);



        //상품 검색
        searchButtton=(Button)findViewById(R.id.searchButton);
        searchText=(EditText)findViewById(R.id.searchText);

        searchButtton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                String searchItem=searchText.getText().toString();

                //공백일 때 처리내용
                if(searchItem.length()==0){
                    return;
                }
                //검색 키워드 입력된 경우
                else{
                    //search.searchResult(searchItem);


                    itemDatabase=FirebaseDatabase.getInstance().getReference("name");

                    Query myTopPostsQuery=itemDatabase.orderByChild("name").equalTo(searchItem);


                   // mAdapter = new RecyclerImageTextAdapter(mSearchList) ;

                    //mAdapter.notifyDataSetChanged() ;
                    //mRecyclerView.setAdapter(mAdapter) ;

                }


            }

        });
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

    public void addSearchItem(Drawable icon, String title, String desc) {
        SamsungItem item = new SamsungItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        mSearchList.add(item);
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