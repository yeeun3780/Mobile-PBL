package com.example.shoppingmall;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Product {
    public String category;
    public String name;
    public int price;
    public String product_info;
    public int product_num;

    public Product() {

    }

    public Product(int product_num, String name, String category,int price ,String product_info) {
      this.product_num = product_num;
      this.name = name;
      this.category = category;
      this.price = price;
      this.product_info = product_info;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("category", category);
        result.put("name", name);
        result.put("price", price);
        result.put("product_info", product_info);
        result.put("product_num", product_num);
        return result;
    }

}
