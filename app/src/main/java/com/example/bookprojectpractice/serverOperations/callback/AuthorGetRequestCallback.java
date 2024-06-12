package com.example.bookprojectpractice.serverOperations.callback;

import com.example.bookprojectpractice.mapper.AuthorMapper;
import com.example.bookprojectpractice.model.Author;
import com.example.bookprojectpractice.serverOperations.apiservice.AuthorApiService;
import com.example.bookprojectpractice.serverOperations.valueapi.AuthorValueApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface AuthorGetRequestCallback extends Callback<AuthorValueApi> {

    void onSuccess(Author author);
    void onFailure(Throwable t);

    @Override
    default void onResponse(Call<AuthorValueApi> call, Response<AuthorValueApi> response){
        if(response.isSuccessful()){
            AuthorValueApi authorValueApi = response.body();
            if(authorValueApi != null){
                Author author = AuthorMapper.toDomainAuthor(authorValueApi);
                onSuccess(author);
            }
        }else{
            onFailure(new Exception("Request failed : " + response.code()));
        }
    }

    @Override
    default void onFailure(Call<AuthorValueApi> call, Throwable t){
        onFailure(t);
    }
}
