package com.ecommerce.retailbiz.notification;

public class SMSNotification implements Notification {
    @Override
    public boolean sendNotification(MessageAttribute messageAttribute) {
        return true;
    }
}
