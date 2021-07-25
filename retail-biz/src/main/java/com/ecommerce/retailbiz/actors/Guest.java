package com.ecommerce.retailbiz.actors;

public class Guest extends Customer {
    public Account createNewAccount() {
        Account account = new Account();
        account.setEmail("fooName@foobar.com");
        account.setPhoneNumber("9812345670");
        account.setName("Foo Bar");
        account.setPassword("********");
        return account;
    }
}
