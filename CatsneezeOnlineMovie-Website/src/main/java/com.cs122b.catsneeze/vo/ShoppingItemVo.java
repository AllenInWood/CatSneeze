package com.cs122b.catsneeze.vo;

public class ShoppingItemVo {

    // Movies list

    // Quantity list;

    private MovieVo movieVo;

    private int quantity;

    public ShoppingItemVo(MovieVo movieVo, int quantity) {
        this.movieVo = movieVo;
        this.quantity = quantity;
    }

    public ShoppingItemVo() {

    }

    public MovieVo getMovieVo() {
        return movieVo;
    }

    public void setMovieVo(MovieVo movieVo) {
        this.movieVo = movieVo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
