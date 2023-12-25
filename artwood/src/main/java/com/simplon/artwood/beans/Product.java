package com.simplon.artwood.beans;

import java.util.Date;
import java.util.Set;

public class Product {
    private int productID;
    private String label;
    private double price ;
    private String description ;
    private Category category;
    private Set<Review> reviews;
    private String store;
    private Date publishDate;


}
