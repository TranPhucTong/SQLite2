package com.example.sqlite2;

public class TenDD {
    private  int id;
    private String tenDD;

    public TenDD(int id, String tenDD) {
        this.id = id;
        this.tenDD = tenDD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDD() {
        return tenDD;
    }

    public void setTenDD(String tenDD) {
        this.tenDD = tenDD;
    }
}
