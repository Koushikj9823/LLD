package com.ecommerce.retailbiz.notification;

public class WhatsAppNotification implements Notification {
    @Override
    public boolean sendNotification(MessageAttribute messageAttribute) {
        return true;
    }
}
