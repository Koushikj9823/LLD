package com.ecommerce.retailbiz.notification;

public class EmailNotification implements Notification {


    @Override
    public boolean sendNotification(MessageAttribute messageAttribute) {
        return true;
    }
}
