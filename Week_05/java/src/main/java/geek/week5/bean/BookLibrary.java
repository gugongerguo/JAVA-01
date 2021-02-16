package geek.week5.bean;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("bookLibrary")
public class BookLibrary implements Library{

    @Override
    public String getBooks(){
        Book book = new Book(3,"《图书管理员的自我修养》");
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        return books.toString();
    }

}
