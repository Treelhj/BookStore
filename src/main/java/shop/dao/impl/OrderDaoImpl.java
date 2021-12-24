package shop.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import shop.dao.OrderDao;
import shop.domain.Order;
import shop.util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void insertOrder(Order order) {
        String sql = "insert into orders values(?,?,?,?,?,?,'N',NULL)";
        template.update(sql, order.getOid(),
                order.getUsername(),
                order.getName(),
                order.getAddress(),
                order.getTelephone(),
                order.getPrice());
    }

    @Override
    public void setOidCids(List cids, String oid) {

        String sql = "insert into cart_orders values(?,?) ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//参数列表
        params.add(oid);
        params.add(cids.get(0));
        for(int i=1;i<cids.size();i++){
            sb.append(",(?,?) ");
            params.add(oid);
            params.add(cids.get(i));
        }
        sql=sb.toString();
        template.update(sql,params.toArray());

    }

    @Override
    public String selectOStatus(String oid) {

        String sql="select status from orders  where oid = ? ";
        return template.queryForObject(sql, String.class, oid);

    }

    @Override
    public void setStatusY(String oid) {
        String sql="update orders set status = 'Y' where oid = ? ";
        template.update(sql,oid);
    }

    @Override
    public List<Integer> selectCidByOid(String oid) {
        String sql="select cid from cart_orders where oid = ? ";
        return template.query(sql,new BeanPropertyRowMapper<Integer>(Integer.class),oid);
    }

    @Override
    public List<Integer> getCidsByOid(String oid) {
        String sql="select cid from cart_orders where oid = ? ";
        return template.queryForList(sql, Integer.class, oid);
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        String sql="select * from orders where username = ? ORDER BY time_stamp DESC ";
        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),username);
    }

    @Override
    public void delCidOid(String oid) {
        String sql="delete from cart_orders where oid = ? ";
        template.update(sql,oid);
    }

    @Override
    public void delOrder(String oid) {
        String sql="delete from orders where oid = ? ";
        template.update(sql,oid);
    }

    @Override
    public List<Order> getAllOrder() {
        String sql="select * from orders ORDER BY time_stamp DESC ";
        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class));
    }
}
