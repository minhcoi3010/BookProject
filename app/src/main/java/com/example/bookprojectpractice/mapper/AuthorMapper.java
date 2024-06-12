package com.example.bookprojectpractice.mapper;

import com.example.bookprojectpractice.model.Author;
import com.example.bookprojectpractice.serverOperations.valueapi.AuthorValueApi;

public class AuthorMapper {

    //lấy giá trị từ api gán cho author
    public static Author toDomainAuthor(AuthorValueApi valueApi){
        Author author = new Author();
        author.setName(valueApi.getName());
        author.setBiographie(valueApi.getBio());
        author.setWikipedia(valueApi.getWikipedia());
        author.setWebsite(valueApi.getWebsite());
        author.setBirthDate(valueApi.getBirth_date());
        author.setDeathDate(valueApi.getDeath_date());
        return author;
    }
}
