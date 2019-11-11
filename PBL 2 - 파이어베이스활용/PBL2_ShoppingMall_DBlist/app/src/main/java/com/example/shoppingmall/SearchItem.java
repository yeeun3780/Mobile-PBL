package com.example.shoppingmall;

import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchItem {
    private DatabaseReference itemDatabase;
    Button button;

    public Query searchResult(String search){
        itemDatabase=FirebaseDatabase.getInstance().getReference("name");

        Query myTopPostsQuery=itemDatabase.orderByChild("name").equalTo(search);

        return myTopPostsQuery;

    }
}
