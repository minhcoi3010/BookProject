package com.example.bookprojectpractice.serverOperations.callback;

import com.example.bookprojectpractice.mapper.BookMapper;
import com.example.bookprojectpractice.model.Book;
import com.example.bookprojectpractice.serverOperations.valueapi.BookValueApi;
import com.example.bookprojectpractice.serverOperations.valueapi.BooksApiResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface BookGetRequestCallback extends Callback<BooksApiResponse> {
    void onSuccess(List<Book> bookList);
    void onFailure(Throwable t);

    @Override
    default void onResponse(Call<BooksApiResponse> call, Response<BooksApiResponse> response) {
        List<Book> bookList = new ArrayList<>();
        if(response.isSuccessful()){
            BooksApiResponse booksApiResponse = response.body();
            if(booksApiResponse != null && booksApiResponse.getBooks() != null){
                for(BookValueApi bookValueApi : booksApiResponse.getBooks()){
                    Book book = BookMapper.toDomainBook(bookValueApi);
                    bookList.add(book);
                }
                onSuccess(bookList);
            }
        }else{
            onFailure(new Exception("Response failed :" + response.code()));
        }
    }

    @Override
    default void onFailure(Call<BooksApiResponse> call, Throwable t) {
        onFailure(t);
    }
}
