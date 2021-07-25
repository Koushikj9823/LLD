package com.ecommerce.retailbiz.cart;

import com.ecommerce.retailbiz.actors.User;
import com.ecommerce.retailbiz.enums.OrderStatus;
import com.ecommerce.retailbiz.enums.PaymentStatus;
import com.ecommerce.retailbiz.notification.NotificationService;
import com.ecommerce.retailbiz.enums.PaymentType;
import com.ecommerce.retailbiz.shipping.Address;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    List<Item> itemList;
    private User user;
    private Address shippingAddress;
    private Date date;
    private NotificationService notificationService;
    private double orderValue;
    private OrderLog orderLogs;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }

    public OrderLog getOrderLogs() {
        return orderLogs;
    }

    public void setOrderLogs(OrderLog orderLogs) {
        this.orderLogs = orderLogs;
    }

    private OrderStatus placeOrder(){
       return this.orderLogs.getOrderStatus();
    };
    private OrderStatus trackOrder(){
        return this.orderLogs.getOrderStatus();
    }

    private PaymentStatus makePayment(PaymentType paymentType){
        return this.getOrderLogs().getPaymentStatus();
    }

}
