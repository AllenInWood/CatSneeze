package com.cs122b.catsneeze.vo;

public class SuggestionVo {

    private SuggestionMeta data;

    private String value;


    public SuggestionVo() {

    }

    public SuggestionVo(SuggestionMeta data, String value) {
        this.data = data;
        this.value = value;
    }

    public SuggestionMeta getData() {
        return data;
    }

    public void setData(SuggestionMeta data) {
        this.data = data;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
