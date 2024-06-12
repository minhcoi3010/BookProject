package com.example.bookprojectpractice.serverOperations.apiservice;

import com.example.bookprojectpractice.serverOperations.valueapi.AuthorValueApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthorApiService {
    // https://openlibrary.org/authors/OL2622837A.json
    @GET("authors/{keySearch}.json")
    Call<AuthorValueApi> getAuthor(
            @Path("keySearch") String keySearch
    );
}
