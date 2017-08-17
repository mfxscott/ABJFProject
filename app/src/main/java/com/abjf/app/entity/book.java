package com.abjf.app.entity;

import java.util.List;

/**
 * Created by NN on 2017/8/12.
 */

public class book {
    private String count;
    private String start;
    private String total;
    private List<books> books;

    public List<books> getBooksList() {
        return books;
    }

    public void setBooksList(List<books> booksList) {
        this.books = booksList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "book{" +
                "count='" + count + '\'' +
                ", start='" + start + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
