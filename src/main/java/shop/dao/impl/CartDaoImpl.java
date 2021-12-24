package shop.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import shop.dao.CartDao;
import shop.domain.Cart;
import shop.util.JDBCUtils;

import java.util.List;
import java.util.Map;


public class CartDaoImpl implements CartDao {

    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public void add(Cart cart) {
        String sql="insert into cart(bid,uid,username,bname,num,price,totalprice,status) values(?,?,?,?,?,?,?,?)";
        template.update(sql,cart.getBid(),
                cart.getUid(),
                cart.getUsername(),
                cart.getBname(),
                cart.getNum(),
                cart.getPrice(),
                cart.getTotalprice(),
                cart.getStatus());
    }

    @Override
    public List<Cart> getByUid(int uid) {
        String sql="select * from cart where uid = ? and status = 'C'";
        return template.query(sql,new BeanPropertyRowMapper<Cart>(Cart.class),uid);
    }

    @Override
    public void delByCid(int cid) {
        String sql="delete from cart where cid = ? ";
        template.update(sql,cid);
    }

    @Override
    public void clearAllByCids(List cids) {
        String datas="";
        StringBuilder sb=new StringBuilder(datas);
        for(int i=0;i<cids.size();i++){
//            System.out.println(cids.get(i));
            if(i==cids.size()-1){
                sb.append(cids.get(i)+"");
            }else{
                sb.append(cids.get(i)+",");
            }
        }
        datas=sb.toString();
//        System.out.println(datas);

        String sql="delete from cart where cid in ( "+datas+")";
        template.update(sql);
    }

    @Override
    public Cart findByBidAndUid(int bid, int uid) {
        Cart c=null;
        try {
            String sql="select * from cart where bid = ? and uid = ? and status = 'C'";
            c=template.queryForObject(sql, new BeanPropertyRowMapper<Cart>(Cart.class),bid,uid);
        } catch (Exception e) {
        }
        return c;
    }

    @Override
    public void addNum(int cid, int num ,double addprice) {
        String sql="update cart set num = num + ? , totalprice = totalprice + ? where cid = ?";
        template.update(sql,num,addprice,cid);
    }

    @Override
    public void setN(List cids) {
        String datas="";
        StringBuilder sb=new StringBuilder(datas);
        for(int i=0;i<cids.size();i++){
            if(i==cids.size()-1){
                sb.append(cids.get(i)+"");
            }else{
                sb.append(cids.get(i)+",");
            }
        }
        datas=sb.toString();
        String sql="update cart set status = 'N' where cid in ("+datas+")";
        template.update(sql);
    }

    @Override
    public void setY(List<Integer> cids) {
        String datas="";
        StringBuilder sb=new StringBuilder(datas);
        for(int i=0;i<cids.size();i++){
            if(i==cids.size()-1){
                sb.append(cids.get(i)+"");
            }else{
                sb.append(cids.get(i)+",");
            }
        }
        datas=sb.toString();
        String sql="update cart set status = 'Y' where cid in ("+datas+")";
        template.update(sql);
    }

    @Override
    public List<Map<String, Object>> getBidNume(List<Integer> cids) {
        String datas="";
        StringBuilder sb=new StringBuilder(datas);
        for(int i=0;i<cids.size();i++){
            if(i==cids.size()-1){
                sb.append(cids.get(i)+"");
            }else{
                sb.append(cids.get(i)+",");
            }
        }
        datas=sb.toString();
        String sql="select bid,num from cart where cid in ("+datas+") ";
        return template.queryForList(sql);

    }

    @Override
    public List<Cart> getByCids(List<Integer> cids) {
        String datas="";
        StringBuilder sb=new StringBuilder(datas);
        for(int i=0;i<cids.size();i++){
            if(i==cids.size()-1){
                sb.append(cids.get(i)+"");
            }else{
                sb.append(cids.get(i)+",");
            }
        }
        datas=sb.toString();
        String sql="select * from cart where cid in ("+datas+") ";
        return template.query(sql,new BeanPropertyRowMapper<Cart>(Cart.class));
    }

    @Override
    public List<Cart> getCartY() {
        String sql="SELECT bname,price,SUM(num) as num FROM cart WHERE STATUS='Y' GROUP BY bid  ORDER BY num DESC ";
        return template.query(sql,new BeanPropertyRowMapper<Cart>(Cart.class));
    }


}
