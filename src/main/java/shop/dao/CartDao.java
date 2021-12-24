package shop.dao;

import shop.domain.Cart;

import java.util.List;
import java.util.Map;

public interface CartDao {
    void add(Cart cart);

    List<Cart> getByUid(int uid);


    void delByCid(int cid);

    void clearAllByCids(List cids);


    Cart findByBidAndUid(int bid, int uid);

    void addNum(int cid, int num,double addprice);

    void setN(List cids);


    void setY(List<Integer> cids);

    List<Map<String,Object>> getBidNume(List<Integer> cids);

    List<Cart> getByCids(List<Integer> cids);

    List<Cart> getCartY();
}
