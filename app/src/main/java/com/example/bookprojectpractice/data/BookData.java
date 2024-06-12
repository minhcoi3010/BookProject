package com.example.bookprojectpractice.data;

import com.example.bookprojectpractice.model.Book;

import java.util.ArrayList;
import java.util.List;


public class BookData {
    private List<Book> books;

    public BookData(){
        books = new ArrayList<>();
    }

    public BookData(List<Book> books){
        this.books = new ArrayList<>();
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Book getBookByKey(String key){
        for (Book book: books) {
            if(book.getKey().equals(key)){
                return book;
            }
        }
        return null;
    }

}
