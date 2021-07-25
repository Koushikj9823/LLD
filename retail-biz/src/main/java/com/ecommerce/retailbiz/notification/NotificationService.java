package com.ecommerce.retailbiz.notification;

public class NotificationService {

    public boolean sendNotification(NotificationDomain notificationDomain) {
        Notification notificationObj;
        MessageAttribute messageAttribute;

        switch (notificationDomain.getNotificationType()) {
            case EMAIL:
                notificationObj = new EmailNotification();
                messageAttribute = new MessageAttribute("retailBiz.email.com", notificationDomain.getUser().getAccount().getEmail(), "Order Detail...");
                break;
            case WHATSAPP:
                notificationObj = new WhatsAppNotification();
                messageAttribute = new MessageAttribute("retailBiz.email.com", notificationDomain.getUser().getAccount().getPhoneNumber(), "Order Detail...");
                break;
            default:
                notificationObj = new SMSNotification();
                messageAttribute = new MessageAttribute("retailBiz.email.com", notificationDomain.getUser().getAccount().getPhoneNumber(), "Order Detail...");
                break;
        }
        return notificationObj.sendNotification(messageAttribute);
    }
}