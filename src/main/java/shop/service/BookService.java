package shop.service;

import shop.domain.Book;
import shop.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface BookService {

    //分页查询书籍
    PageBean<Book> pageQuery(int currentPage,String bword);
    //根据bid查询书籍
    Book findByBid(String bidstr);

    void newNum(List<Map<String, Object>> lists);

    List<Book> getAllBook();

    void setN(int bid);

    void addBook(Book book);

    void updateBook(Book book);
}
