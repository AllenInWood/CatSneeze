package com.cs122b.catsneeze.vo;

public class MetadataVo {

    private String attribute;

    private String category;

    public MetadataVo(String attribute, String category) {
        this.attribute = attribute;
        this.category = category;
    }

    public MetadataVo() {

    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
