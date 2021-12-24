package shop.service.impl;

import shop.dao.BookDao;
import shop.dao.impl.BookDaoImpl;
import shop.domain.Book;
import shop.domain.PageBean;
import shop.service.BookService;

import java.util.List;
import java.util.Map;

public class BookServiceImpl implements BookService {

    private BookDao bookDao=new BookDaoImpl();
    //分页查询书籍
    @Override
    public PageBean<Book> pageQuery(int currentPage,String bword) {
        PageBean<Book> pb=new PageBean<Book>();
        pb.setPageSize(8);
        pb.setCurrentPage(currentPage);
        int totalCount=bookDao.getTotalCount(bword);
        int totalPage=totalCount%8 == 0? totalCount/8:totalCount/8+1;
        pb.setTotalCount(totalCount);
        pb.setTotalPage(totalPage);
        int start=(currentPage-1)*8;
        List<Book> list=bookDao.findByPage(start,bword);
        pb.setList(list);
        return pb;
    }

    //根据bid查询书籍
    @Override
    public Book findByBid(String bidstr) {
        int bid=Integer.parseInt(bidstr);
        return bookDao.findByBid(bid);
    }

    @Override
    public void newNum(List<Map<String, Object>> lists) {
        bookDao.newNum(lists);
    }

    @Override
    public List<Book> getAllBook() {
        return bookDao.getAllBook();
    }

    @Override
    public void setN(int bid) {
        bookDao.setN(bid);
    }

    @Override
    public void addBook(Book book) {
        bookDao.insertOne(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateOne(book);
    }
}
