package com.ecommerce.retailbiz.search;

import com.ecommerce.retailbiz.product.Product;
import java.util.List;

public abstract class Search {
    public abstract List<Product> searchProductsByName(String name);
    public abstract List<Product> searchProductsByCategory(String category);
}
