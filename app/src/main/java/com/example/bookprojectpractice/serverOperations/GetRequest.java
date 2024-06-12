package com.example.bookprojectpractice.serverOperations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.bookprojectpractice.data.ImageData;
import com.example.bookprojectpractice.model.Book;
import com.example.bookprojectpractice.serverOperations.apiservice.AuthorApiService;
import com.example.bookprojectpractice.serverOperations.apiservice.BookApiService;
import com.example.bookprojectpractice.serverOperations.callback.AuthorGetRequestCallback;
import com.example.bookprojectpractice.serverOperations.callback.BookGetRequestCallback;
import com.example.bookprojectpractice.serverOperations.callback.ImageBookGetRequestCallback;
import com.example.bookprojectpractice.serverOperations.valueapi.AuthorValueApi;
import com.example.bookprojectpractice.serverOperations.valueapi.BooksApiResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRequest {

    private static final String BASE_URL = "https://openlibrary.org/";
    private static final String BASE_URL_COVER = "https://covers.openlibrary.org/";

    public void retrBooks(String searchName, int limit, BookGetRequestCallback callback){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookApiService bookApiService = retrofit.create(BookApiService.class);
        Call<BooksApiResponse> call = bookApiService.getBooks(searchName, limit);
        call.enqueue(callback);
    }

    public void retrBookImage(Book book, ImageBookGetRequestCallback callback){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL_COVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookApiService bookApiService = retrofit.create(BookApiService.class);
        if (book.getIsbnList() != null && !book.getIsbnList().isEmpty()) {
            retrieveImageForIsbn(bookApiService, book, callback, book.getIsbnList(), 0);
        } else {
            callback.onFailure();
        }
    }

    private void retrieveImageForIsbn(BookApiService bookApiService, Book book, ImageBookGetRequestCallback callback, List<String> isbnList, int i)  {

        if(i >= isbnList.size()){
            callback.onFailure();
            return;
        }

        String isbn = isbnList.get(i);
        Call<ResponseBody> call = bookApiService.getImageBook(isbn);

        ImageBookGetRequestCallback.ImageResponseHandler responseHandler =
                new ImageBookGetRequestCallback.ImageResponseHandler(callback, book, isbn){
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            String contentType = response.headers().get("content-type");
                            if (book.getPrincipalIsbn() == null) {
                                if (contentType != null && contentType.equals("image/jpeg")) {
                                    Bitmap image = GetRequest.convertResponseBodyToBitmap(response.body());
                                    //thêm anảnh vào trong ImageData tương ứng với isbn
                                    ImageData.getInstance().addImage(isbn, image);
                                    book.setPrincipalIsbn(isbn);
                                    callback.onSuccess(image); // Passer les données au callback
                                    return;
                                }
                            }
                        }
                        //Nếu cuộc gọi API không thành công hoặc nội dung không phải là hình ảnh JPEG,
                        // thực hiện đệ quy để thử với ISBN tiếp theo trong danh sách.
                        retrieveImageForIsbn(bookApiService, book, callback, isbnList, i + 1);
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        retrieveImageForIsbn(bookApiService, book, callback, isbnList, i + 1);
                    }
                };
        call.enqueue(responseHandler);
    }
    public void retrAuthor(String authorKey, AuthorGetRequestCallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthorApiService apiService = retrofit.create(AuthorApiService.class);
        Call<AuthorValueApi> call = apiService.getAuthor(authorKey);

        call.enqueue(callback);
    }

    public static Bitmap convertResponseBodyToBitmap(ResponseBody responseBody) {
        Bitmap bitmap = null;
        try {
            byte[] bytes = responseBody.bytes();
            //chuyển đổi từ bytes sang bitmap
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
