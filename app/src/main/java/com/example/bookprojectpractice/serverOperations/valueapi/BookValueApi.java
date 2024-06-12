package com.example.bookprojectpractice.serverOperations.valueapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookValueApi {
    @JsonProperty("title")
    private String title;

    @JsonProperty("key")
    private String key;

    @JsonProperty("author_name")
    private List<String> author_name;

    @JsonProperty("author_key")
    private List<String> author_key;

    @JsonProperty("number_of_pages_median")
    private String number_of_pages_median;

    @JsonProperty("isbn")
    private List<String> isbn;

    @JsonProperty("seed")
    private List<String> seed;

    @JsonProperty("first_publish_year")
    private String first_publish_year;

    public BookValueApi(String title, String key, List<String> author_name, List<String> author_key, String number_of_pages_median, List<String> isbn, List<String> seed, String first_publish_year) {
        this.title = title;
        this.key = key;
        this.author_name = author_name;
        this.author_key = author_key;
        this.number_of_pages_median = number_of_pages_median;
        this.isbn = isbn;
        this.seed = seed;
        this.first_publish_year = first_publish_year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(List<String> author_name) {
        this.author_name = author_name;
    }

    public List<String> getAuthor_key() {
        return author_key;
    }

    public void setAuthor_key(List<String> author_key) {
        this.author_key = author_key;
    }

    public String getNumber_of_pages_median() {
        return number_of_pages_median;
    }

    public void setNumber_of_pages_median(String number_of_pages_median) {
        this.number_of_pages_median = number_of_pages_median;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(List<String> isbn) {
        this.isbn = isbn;
    }

    public List<String> getSeed() {
        return seed;
    }

    public void setSeed(List<String> seed) {
        this.seed = seed;
    }

    public String getFirst_publish_year() {
        return first_publish_year;
    }

    public void setFirst_publish_year(String first_publish_year) {
        this.first_publish_year = first_publish_year;
    }

    @Override
    public String toString() {
        return "BookValueApi{" +
                "title='" + title + '\'' +
                ", key='" + key + '\'' +
                ", author_name=" + author_name +
                ", author_key=" + author_key +
                ", number_of_pages_median='" + number_of_pages_median + '\'' +
                ", isbn=" + isbn +
                ", seed=" + seed +
                ", first_publish_year='" + first_publish_year + '\'' +
                '}';
    }
}
