package shop.service;

import shop.domain.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    void setCartOrder(List cids, String oid);

    String getOStatus(String oid);

    void setOrderY(String oid);

    List<Integer> getCidsByOid(String oid);

    List<Order> getOrders(String username);

    void delCidOid(String oid);

    void delOrder(String oid);

    List<Order> getAllOrder();
}
