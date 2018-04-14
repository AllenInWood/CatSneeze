package com.cs122b.catsneeze.vo;


import com.cs122b.catsneeze.pojo.Genres;
import com.cs122b.catsneeze.pojo.Ratings;
import com.cs122b.catsneeze.pojo.Stars;

import java.util.List;

public class MovieVo {

//    title
//    year;
//    director;
//    list of genres;
//    list of stars
//    rating
    private String id;

    private String title;

    private Integer year;

    private String director;

    private List<Genres> genresList;

    private List<Stars> starsList;

    private Ratings ratings;

    public MovieVo(String id, String title, Integer year, String director, List<Genres> genresList, List<Stars> starsList, Ratings ratings) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.genresList = genresList;
        this.starsList = starsList;
        this.ratings = ratings;
    }

    public MovieVo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Genres> getGenresList() {
        return genresList;
    }

    public void setGenresList(List<Genres> genresList) {
        this.genresList = genresList;
    }

    public List<Stars> getStarsList() {
        return starsList;
    }

    public void setStarsList(List<Stars> starsList) {
        this.starsList = starsList;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }
}
