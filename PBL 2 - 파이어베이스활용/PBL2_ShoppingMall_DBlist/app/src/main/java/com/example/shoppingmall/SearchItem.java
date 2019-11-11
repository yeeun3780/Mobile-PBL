package com.example.shoppingmall;

import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchItem {
    private DatabaseReference itemDatabase;
    Button button;

    public void searchResult(String search){
        itemDatabase=FirebaseDatabase.getInstance().getReference();

        Query myTopPostsQuery=itemDatabase.child("name").orderByChild(search);
    }
}
