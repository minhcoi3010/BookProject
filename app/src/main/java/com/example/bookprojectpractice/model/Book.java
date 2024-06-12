package com.example.bookprojectpractice.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(tableName = "Books")
public class Book {
    private int id;

    @PrimaryKey
    @NonNull
    private String key;

    private String title;

    private String principalIsbn;

    private List<String> isbnList = new ArrayList<>();

    private String firstPublishYear;

    private String link;
    private String pageNumber;

    private String authorName;

    private String authorKey;

    private boolean isStar;

    public Book(){

    }
    public Book(int id, String title){
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrincipalIsbn() {
        return principalIsbn;
    }

    public void setPrincipalIsbn(String principalIsbn) {
        this.principalIsbn = principalIsbn;
    }

    public List<String> getIsbnList() {
        return isbnList;
    }

    public void setIsbnList(List<String> isbnList) {
        this.isbnList = isbnList;
    }

    public String getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(String firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean star) {
        isStar = star;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(key, book.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, title, principalIsbn, isbnList, firstPublishYear, pageNumber, authorName, authorKey, isStar);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", principalIsbn='" + principalIsbn + '\'' +
                ", isbnList=" + isbnList +
                ", firstPublishYear='" + firstPublishYear + '\'' +
                ", pageNumber='" + pageNumber + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorKey='" + authorKey + '\'' +
                '}';
    }
}
