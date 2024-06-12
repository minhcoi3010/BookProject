package com.example.bookprojectpractice.data;

import com.example.bookprojectpractice.model.Author;

import java.util.HashMap;

public class AuthorData {

    private static AuthorData instance;

    private HashMap<String, Author> authorHashMap;

    private AuthorData(){
        authorHashMap = new HashMap<>();
    }

    public static AuthorData getInstance(){
        if (instance == null){
            instance = new AuthorData();
        }
        return instance;
    }

    public Author getAuthor(String key){
        return authorHashMap.get(key);
    }

    public void addAuthor(Author author){
        authorHashMap.put(author.getKey(),author);
    }

    public Boolean haveAuthor(String authorKey){
        return  authorHashMap.containsKey(authorKey);
    }

}
