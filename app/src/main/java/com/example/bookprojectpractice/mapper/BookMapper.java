package com.example.bookprojectpractice.mapper;

import com.example.bookprojectpractice.model.Book;
import com.example.bookprojectpractice.serverOperations.valueapi.BookValueApi;

public class BookMapper {
    public static Book toDomainBook(BookValueApi valueApi) {
        Book book = new Book();
        book.setTitle(valueApi.getTitle());
        book.setIsbnList(valueApi.getIsbn());
        book.setAuthorName(valueApi.getAuthor_name() != null && !valueApi.getAuthor_name().isEmpty() ? valueApi.getAuthor_name().get(0) : "");
        book.setAuthorKey(valueApi.getAuthor_key() != null && !valueApi.getAuthor_key().isEmpty() ? valueApi.getAuthor_key().get(0) : "");
        book.setFirstPublishYear(valueApi.getFirst_publish_year());
        book.setPageNumber(valueApi.getNumber_of_pages_median());
        String link;
        //Xử lý URL dựa trên dữ liệu có sẵn từ BookValueApi.
        // Nếu seed không rỗng, thì tạo một link dựa trên nó.
        // Nếu không, sử dụng một link mặc định.

        if(valueApi.getSeed() != null){
            //concat : hàm nối chuỗi : nối link api vs seed để lấy dữ liệu
            link = "https://openlibrary.org".concat(valueApi.getSeed().get(0));
        }else {
            link = "https://openlibrary.org";
        }
        //Gán giá trị link đã xử lý vào thuộc tính link của đối tượng Book.
        book.setLink(link);

        String key = valueApi.getKey();
        //Xử lý key của Book từ giá trị key của BookValueApi.
        // Lấy phần cuối của key bắt đầu từ "/works/".

        //Nếu key là "path/to/works/document.txt":
        //index sẽ là 14 (7 + 7, bắt đầu ngay sau "/works/").
        //effectiveKey sẽ là "document.txt".
        int index = key.indexOf("/works/") + "/works/".length();
        String effectiveKey = key.substring(index);

        book.setKey(effectiveKey);
        return book;
    }
}
