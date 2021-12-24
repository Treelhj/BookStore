package shop.service;

import shop.domain.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {

    void addCart(int bid, int uid, int num, String username);

    List<Cart> getCart(int uid);

    void delCart(int cid);

    void clearCart(List cids);

    void setN(List cids);

    void setY(List<Integer> cids);

    List<Map<String,Object>> getBidNum(List<Integer> cids);

    List<Cart> getByCids(List<Integer> cids);

    List<Cart> getRank();
}
