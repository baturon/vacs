package com.example.vacs.models;

public enum NameWork {
    CHANGE_OIL("Замена масла"),
    CHANGE_GRM("Замена ГРМ");



    private String title;

     NameWork(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }



    @Override
    public String toString() {
        return "" + title;
    }
}
