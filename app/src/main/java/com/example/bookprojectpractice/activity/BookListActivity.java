package com.example.bookprojectpractice.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookprojectpractice.R;
import com.example.bookprojectpractice.adapter.BookListAdapter;
import com.example.bookprojectpractice.data.AuthorData;
import com.example.bookprojectpractice.data.BookData;
import com.example.bookprojectpractice.database.AppDatabase;
import com.example.bookprojectpractice.database.BookDao;
import com.example.bookprojectpractice.model.Author;
import com.example.bookprojectpractice.model.Book;
import com.example.bookprojectpractice.serverOperations.GetRequest;
import com.example.bookprojectpractice.serverOperations.callback.AuthorGetRequestCallback;
import com.example.bookprojectpractice.serverOperations.callback.BookGetRequestCallback;
import com.example.bookprojectpractice.serverOperations.callback.ImageBookGetRequestCallback;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView rvRandomList;
    private RecyclerView rvNewBooks;
    private RecyclerView rvFavorite;

    private int cardViewId = R.layout.view_book_card;
    private BookListAdapter adapterRandomList;
    private BookListAdapter adapterNewList;
    private BookListAdapter adapterFavorite;

    public static BookData randomList;
    public static BookData newList;

    public static BookData favoriteList;
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        ImageView ivSearchImageView = findViewById(R.id.ivSearch);
        ivSearchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookListActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        GetRequest getRequest = new GetRequest();
        GetRequest getRequest2 = new GetRequest();

        rvRandomList = findViewById(R.id.rvRandomList);
        rvNewBooks = findViewById(R.id.rvNewBooks);
        rvFavorite = findViewById(R.id.rvFavorites);

        //gọi csdl
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        BookDao bookDao = db.bookDao();

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                List<Book> favoriteBooks = bookDao.getAllBooks();
                favoriteList = new BookData(favoriteBooks);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                adapterFavorite = new BookListAdapter(cardViewId, favoriteList.getBooks(),"FavoriteList");
                rvFavorite.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvFavorite.setAdapter(adapterFavorite);
                super.onPostExecute(unused);
            }
        }.execute();

        String randomWord = UtilsWord.getRandomWord(this);
        TextView tvRandomWord = findViewById(R.id.tvRandomWord);
        if(!randomWord.isEmpty()){
            tvRandomWord.setText(randomWord);
        }


        getRequest.retrBooks(randomWord, 15, new BookGetRequestCallback() {
            @Override
            public void onSuccess(List<Book> bookList) {

                randomList = new BookData(bookList);
                adapterRandomList = new BookListAdapter(cardViewId, randomList.getBooks(), "RandomList");
                rvRandomList.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvRandomList.setAdapter(adapterRandomList);

                for(Book bookItem : randomList.getBooks()){
                    GetRequest getRequestImage = new GetRequest();
                    getRequestImage.retrBookImage(bookItem, new ImageBookGetRequestCallback() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            rvRandomList.setAdapter(adapterRandomList);
                        }

                        @Override
                        public void onFailure() {

                        }
                    });

                    String authorKey = bookItem.getAuthorKey();
                    if(AuthorData.getInstance().haveAuthor(authorKey)){
                        GetRequest getRequestAuthor = new GetRequest();
                        getRequestAuthor.retrAuthor(authorKey, new AuthorGetRequestCallback() {
                            @Override
                            public void onSuccess(Author author) {
                                author.setKey(authorKey);
                                AuthorData.getInstance().addAuthor(author);
                            }

                            @Override
                            public void onFailure(Throwable t) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        //gọi api để lấy dữ liệu cho vào newlist
        getRequest2.retrBooks("The Lord of the Rings", 15, new BookGetRequestCallback() {
            @Override
            public void onSuccess(List<Book> bookList) {

                newList  = new BookData();

                adapterNewList = new BookListAdapter(cardViewId, new ArrayList<>(bookList),"NewBooks");
                rvNewBooks.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
                rvNewBooks.setAdapter(adapterNewList);

                for(Book book : newList.getBooks()){
                    GetRequest getRequestImage = new GetRequest();
                    getRequestImage.retrBookImage(book, new ImageBookGetRequestCallback() {
                        @Override
                        public void onSuccess(Bitmap bitmap) {
                            rvNewBooks.setAdapter(adapterNewList);
                        }

                        @Override
                        public void onFailure() {

                        }
                    });
                    String authorKey = book.getAuthorKey();
                    if(!AuthorData.getInstance().haveAuthor(authorKey)){
                        GetRequest getRequestAuthor = new GetRequest();

                        getRequestAuthor.retrAuthor(authorKey, new AuthorGetRequestCallback() {
                            @Override
                            public void onSuccess(Author author) {
                                author.setKey(authorKey);
                                AuthorData.getInstance().addAuthor(author);
                            }

                            @Override
                            public void onFailure(Throwable t) {
                            }
                        });
                    }
                }

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        adapterFavorite = new BookListAdapter(cardViewId, favoriteList.getBooks(), "FavoriteList");
        rvFavorite.setLayoutManager(new LinearLayoutManager(BookListActivity.this, LinearLayoutManager.HORIZONTAL, false));
        rvFavorite.setAdapter(adapterFavorite);
    }

}