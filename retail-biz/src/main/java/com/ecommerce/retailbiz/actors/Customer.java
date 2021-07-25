package com.ecommerce.retailbiz.actors;

import com.ecommerce.retailbiz.cart.ShoppingCart;
import com.ecommerce.retailbiz.search.Search;

public abstract class Customer {
    private ShoppingCart cart;
    private Search searchObj;
    private int customerID;

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Search getSearchObj() {
        return searchObj;
    }

    public void setSearchObj(Search searchObj) {
        this.searchObj = searchObj;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public ShoppingCart getShoppingCart(){
        ShoppingCart cart = new ShoppingCart();
        return cart;
    }
    public void addItemsToCart(){
    }
    public void updateItemsToCart(){
    }
    public void deleteItemsToCart(){
    }
}
