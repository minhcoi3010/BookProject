package com.example.bookprojectpractice.serverOperations.callback;

import android.graphics.Bitmap;

import com.example.bookprojectpractice.serverOperations.GetRequest;
import com.example.bookprojectpractice.data.ImageData;
import com.example.bookprojectpractice.model.Book;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface ImageBookGetRequestCallback {

    void onSuccess(Bitmap bitmap);
    void onFailure();

    class ImageResponseHandler implements Callback<ResponseBody>{

        private final ImageBookGetRequestCallback callback;
        private final Book book;
        private final String isbn;

        public ImageResponseHandler(ImageBookGetRequestCallback callback, Book book, String isbn) {
            this.callback = callback;
            this.book = book;
            this.isbn = isbn;
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if(response.isSuccessful()){
                String contentType = response.headers().get("content-type");
                if(book.getPrincipalIsbn() == null){
                    if(contentType != null &&  contentType.equals("image/jpeg")){
                        Bitmap image = GetRequest.convertResponseBodyToBitmap(response.body());
                        ImageData.getInstance().addImage(isbn, image);
                        book.setPrincipalIsbn(isbn);
                        callback.onSuccess(image);
                        return;
                    }
                }else{
                    callback.onFailure();
                }

            }

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            callback.onFailure();
        }
    }


}
