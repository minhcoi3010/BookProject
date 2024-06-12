package com.example.bookprojectpractice.serverOperations.valueapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorValueApi {
    @JsonProperty("name")
    private String name;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("wikipedia")
    private String wikipedia;

    @JsonProperty("website")
    private String website;

    @JsonProperty("birth_date")
    private String birth_date;

    @JsonProperty("death_date")
    private String death_date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getDeath_date() {
        return death_date;
    }

    public void setDeath_date(String death_date) {
        this.death_date = death_date;
    }

    @Override
    public String toString() {
        return "AuthorValueApi{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", wikipedia='" + wikipedia + '\'' +
                ", website='" + website + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", death_date='" + death_date + '\'' +
                '}';
    }
}
