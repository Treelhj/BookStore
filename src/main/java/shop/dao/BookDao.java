package shop.dao;

import shop.domain.Book;
import java.util.List;
import java.util.Map;

public interface BookDao {
    //分页查询书籍
    List<Book> findByPage(int start,String bword);
    //查询书籍总数
    int getTotalCount(String bword);
    //根据bid查询书籍
    Book findByBid(int bid);

    void newNum(List<Map<String, Object>> lists);

    List<Book> getAllBook();

    void setN(int bid);

    void insertOne(Book book);

    void updateOne(Book book);
}
