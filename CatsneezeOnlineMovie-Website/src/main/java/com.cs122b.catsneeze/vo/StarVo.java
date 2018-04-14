package com.cs122b.catsneeze.vo;

import com.cs122b.catsneeze.pojo.Movies;

import java.util.List;

public class StarVo {

//    id
//    name
//    year;
//    list of movies;

    private String id;

    private String name;

    private Integer birthYear;

    private List<Movies> moviesList;

    public StarVo(String id, String name, Integer birthYear, List<Movies> moviesList) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.moviesList = moviesList;
    }

    public StarVo() {

    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public List<Movies> moviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movies> moviesList) {
        this.moviesList = moviesList;
    }

}
