package shop.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import shop.dao.BookDao;
import shop.domain.Book;
import shop.domain.PageBean;
import shop.util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookDaoImpl implements BookDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    //分页查询书籍
    @Override
    public List<Book> findByPage(int start,String bword) {

        String sql="select * from book where 1 = 1 ";
        StringBuilder sb=new StringBuilder(sql);
        List params=new ArrayList();//条件

        if (bword != null && bword.length()>0){
            sb.append(" and ( bname like ? or bintroduce like ? ) ");
            params.add("%"+bword+"%");
            params.add("%"+bword+"%");
        }

        sb.append(" and bstatus = 'Y' ");

        sb.append(" limit ? , ? ");//分页
        params.add(start);
        params.add(8);

        sql=sb.toString();
    /*    System.out.println(start);
        System.out.println(sql);
        System.out.println(params.toArray().toString());*/
        return template.query(sql,new BeanPropertyRowMapper<Book>(Book.class),params.toArray());
    }

    //查询书籍总数
    @Override
    public int getTotalCount(String bword) {
        String sql="select count(*) from book where 1 = 1 ";
        StringBuilder sb=new StringBuilder(sql);

        List params=new ArrayList();//条件
        if (bword != null && bword.length()>0){
            sb.append(" and ( bname like ? or bintroduce like ? ) ");
            params.add("%"+bword+"%");
            params.add("%"+bword+"%");
        }
        sb.append(" and bstatus = 'Y' ");
        sql=sb.toString();
//        System.out.println(sql);
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    //根据bid查询书籍
    @Override
    public Book findByBid(int bid) {
        String sql="select * from book where bid = ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Book>(Book.class),bid);
    }

    @Override
    public void newNum(List<Map<String, Object>> lists) {
        String s="update book set bcount = bcount - ? where bid = ? ";
        for (Map<String, Object> list : lists) {
//            System.out.println( list.get("bid")+"  "+ list.get("num"));
            template.update(s,list.get("num"),list.get("bid"));
        }
    }

    @Override
    public List<Book> getAllBook() {
        String sql="select * from book where bstatus = 'Y' ";
        return template.query(sql,new BeanPropertyRowMapper<Book>(Book.class));
    }

    @Override
    public void setN(int bid) {
        String sql="update book set bstatus = 'N' where bid = ? ";
        template.update(sql,bid);
    }

    @Override
    public void insertOne(Book book) {
        String sql="insert into book(bid,bname,bintroduce,bcount,bprice,bimgid,bstatus) values(null,?,?,?,?,?,'Y')";
        template.update(sql,book.getBname(),
                book.getBintroduce(),
                book.getBcount(),
                book.getBprice(),
                book.getBimgid()
                );
    }

    @Override
    public void updateOne(Book book) {
        String sql="update book set bname = ? ,bintroduce = ? , bcount = ?, bprice = ?, bimgid = ? where bid = ?";
        template.update(sql,book.getBname(),
                book.getBintroduce(),
                book.getBcount(),
                book.getBprice(),
                book.getBimgid(),
                book.getBid()
        );
    }
}
