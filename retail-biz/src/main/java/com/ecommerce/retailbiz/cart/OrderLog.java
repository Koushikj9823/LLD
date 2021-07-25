package com.ecommerce.retailbiz.cart;

import com.ecommerce.retailbiz.enums.OrderStatus;
import com.ecommerce.retailbiz.enums.PaymentStatus;

import java.util.Date;

public class OrderLog {
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private String orderDetail;
    private Date date;

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
