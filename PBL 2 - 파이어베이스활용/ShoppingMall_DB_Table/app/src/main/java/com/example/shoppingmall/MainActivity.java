package com.example.shoppingmall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity { ;
    private DatabaseReference mDatabase;


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


    }
    private  void writeProduct(String productId,int product_num, String name, String category,int price ,String product_info){
        Product product = new Product(product_num,name,category, price ,product_info);
        mDatabase.child("product").child(productId).setValue(product);
    }
}