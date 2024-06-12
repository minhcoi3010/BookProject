package com.example.bookprojectpractice.serverOperations.valueapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksApiResponse {

    @SerializedName("docs")
    private List<BookValueApi> books;

    public List<BookValueApi> getBooks() {
        return books;
    }
}
