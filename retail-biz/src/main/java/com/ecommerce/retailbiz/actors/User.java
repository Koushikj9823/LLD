package com.ecommerce.retailbiz.actors;

import com.ecommerce.retailbiz.actors.Customer;

public class User extends Customer {
    private Account account;

    public Account getAccount() {
        return account;
    }
}
