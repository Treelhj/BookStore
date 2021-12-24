package shop.dao;

import shop.domain.Order;

import java.util.List;

public interface OrderDao {
    void insertOrder(Order order);

    void setOidCids(List cids, String oid);

    String selectOStatus(String oid);

    void setStatusY(String oid);

    List<Integer> selectCidByOid(String oid);

    List<Integer> getCidsByOid(String oid);

    List<Order> getOrdersByUsername(String username);

    void delCidOid(String oid);

    void delOrder(String oid);

    List<Order> getAllOrder();
}
