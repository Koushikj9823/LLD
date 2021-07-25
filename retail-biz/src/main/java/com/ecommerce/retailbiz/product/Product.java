package com.ecommerce.retailbiz.product;

import com.ecommerce.retailbiz.actors.Seller;
import com.ecommerce.retailbiz.enums.ProductCategory;

import java.util.List;

public class Product {

    private String productId;
    private String productName;
    private String productDescription;
    private ProductCategory productCategory;
    private double price;
    private  int quantity;
    private Seller seller;
    private List<ProductReview> reviewList;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<ProductReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ProductReview> reviewList) {
        this.reviewList = reviewList;
    }
}
