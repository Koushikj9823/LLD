package com.ecommerce.retailbiz.notification;

import com.ecommerce.retailbiz.actors.User;

public class NotificationDomain {
   private  String notificationId;
   private NotificationType notificationType;
   private User user;

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
