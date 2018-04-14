package com.cs122b.catsneeze.vo;

public class TestVo {

    private String name;

    private String info;

    public TestVo(String name, String info) {
        this.name = name;
        this.info = info;
    }
    public TestVo() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
