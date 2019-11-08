package com.example.shoppingmall;

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

}
