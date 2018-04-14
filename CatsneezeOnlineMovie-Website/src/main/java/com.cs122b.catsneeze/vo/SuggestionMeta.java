package com.cs122b.catsneeze.vo;

public class SuggestionMeta {

    private String category;

    private String id;

    public SuggestionMeta(){

    }

    public SuggestionMeta(String category, String id) {
        this.category = category;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
