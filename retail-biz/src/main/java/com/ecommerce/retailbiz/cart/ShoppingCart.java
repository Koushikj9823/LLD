package com.ecommerce.retailbiz.cart;

import java.util.List;

public class ShoppingCart {

    private List<Item> items;
    private double cartValue;

    public List<Item> getItems() {
        return items;
    }

    public double getCartValue() {
        return cartValue;
    }

    private void addItems(Item item){};
    private void updateItems(Item item){};
    private void deleteItems(Item item){};


}
