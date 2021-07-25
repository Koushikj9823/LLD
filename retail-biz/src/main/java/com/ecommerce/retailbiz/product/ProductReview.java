package com.ecommerce.retailbiz.product;

import com.ecommerce.retailbiz.actors.User;

public class ProductReview {
    private String review;
    private double rating;
    private User reviewer;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }
}
