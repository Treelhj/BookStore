package shop.service.impl;

import shop.dao.OrderDao;
import shop.dao.impl.OrderDaoImpl;
import shop.domain.Order;
import shop.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao=new OrderDaoImpl();

    @Override
    public void addOrder(Order order) {
        orderDao.insertOrder(order);
    }

    @Override
    public void setCartOrder(List cids, String oid) {
        orderDao.setOidCids(cids,oid);
    }

    @Override
    public String getOStatus(String oid) {
        return orderDao.selectOStatus(oid);
    }

    @Override
    public void setOrderY(String oid) {
        orderDao.setStatusY(oid);
    }

    @Override
    public List<Integer> getCidsByOid(String oid) {
        return orderDao.getCidsByOid(oid);
    }

    @Override
    public List<Order> getOrders(String username) {
        return orderDao.getOrdersByUsername(username);
    }

    @Override
    public void delCidOid(String oid) {
        orderDao.delCidOid(oid);
    }

    @Override
    public void delOrder(String oid) {
        orderDao.delOrder(oid);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDao.getAllOrder();
    }
}
