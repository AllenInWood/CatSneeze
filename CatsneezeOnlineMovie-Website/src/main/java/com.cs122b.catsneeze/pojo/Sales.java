package com.cs122b.catsneeze.pojo;

import java.util.Date;

public class Sales {

    private int id;

    private int customerId;

    private String movieId;

    private Date saleDate;

    public Sales(int id, int customerId, String movieId, Date saleDate) {
        this.id = id;
        this.customerId = customerId;
        this.movieId = movieId;
        this.saleDate = saleDate;
    }

    public Sales() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
