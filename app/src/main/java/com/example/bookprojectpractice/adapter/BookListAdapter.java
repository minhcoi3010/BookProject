package com.example.bookprojectpractice.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookprojectpractice.R;
import com.example.bookprojectpractice.activity.BookActivity;
import com.example.bookprojectpractice.data.ImageData;
import com.example.bookprojectpractice.model.Book;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder>{

    private final List<Book> localDataSet;
    //Context context;
    private int resourceId;
    //the Id of the layout we will repeat as many times we have items in the list

    private String listName;

    public BookListAdapter( int resourceId, List<Book> books, String listName){
        //this.context = context;
        this.localDataSet = books;
        this.resourceId = resourceId;
        this.listName = listName;
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(resourceId,parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        if(localDataSet != null && position < localDataSet.size()){
            Book book = localDataSet.get(position);
            holder.tvBookTitle.setText(book.getTitle().toString());
            String isbn = book.getPrincipalIsbn();
            if(isbn != null){
                ImageView imageView = holder.itemView.findViewById(R.id.imageView);
                Bitmap bitmap = ImageData.getInstance().getImage(isbn);
                if(bitmap != null){
                    imageView.setImageBitmap(bitmap);
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        String keybook = book.getKey();
                        if(keybook != null){
                            Intent intent = new Intent(v.getContext(), BookActivity.class);
                            intent.putExtra("BOOK_KEY", keybook);
                            intent.putExtra("BookListName", listName);
                            //gửi dữ liệu qua intent
                            v.getContext().startActivity(intent);
                        }else{
                            // Afficher un message d'alerte si keyBook est nulle
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            //lỗi trên thông tin của sách
                            builder.setMessage("Erreur sur les informartions du livre !");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        TextView tvBookTitle;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
        }
    }
    public void updateBooks(List<Book> newBooks) {
        localDataSet.clear();
        localDataSet.addAll(newBooks);
        notifyDataSetChanged();
    }
}
