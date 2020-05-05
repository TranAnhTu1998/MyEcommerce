package com.example.myecommerce;

public class Product {
    private  int id;
    private String name;
    private float price;
    private int amount_in_warehouse;

    public Product(int id, String name, float price, int amount_in_warehouse){
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount_in_warehouse  = amount_in_warehouse;
    }

    public Product(String name, float price, int amount_in_warehouse){
        this.name = name;
        this.price = price;
        this.amount_in_warehouse  = amount_in_warehouse;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getAmountInWarehouse() {
        return amount_in_warehouse;
    }
}
