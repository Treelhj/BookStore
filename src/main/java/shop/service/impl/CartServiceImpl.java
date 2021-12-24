package shop.service.impl;

import shop.dao.BookDao;
import shop.dao.CartDao;
import shop.dao.impl.BookDaoImpl;
import shop.dao.impl.CartDaoImpl;
import shop.domain.Book;
import shop.domain.Cart;
import shop.service.CartService;

import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {
    private CartDao cartDao =new CartDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public void addCart(int bid, int uid, int num, String username) {
        Cart c=cartDao.findByBidAndUid(bid,uid);
        if(c != null){
            cartDao.addNum(c.getCid(),num,num*c.getPrice());
            return;
        }
        Cart cart=new Cart();
        cart.setBid(bid);
        cart.setUid(uid);
        cart.setNum(num);
        cart.setUsername(username);

        Book book = bookDao.findByBid(bid);
        cart.setBname(book.getBname());
        cart.setPrice(book.getBprice());
        cart.setTotalprice(book.getBprice()*num);
        cart.setStatus("C");

        cartDao.add(cart);
    }

    @Override
    public List<Cart> getCart(int uid) {
        return cartDao.getByUid(uid);
    }

    @Override
    public void delCart(int cid) {
        cartDao.delByCid(cid);
    }

    @Override
    public void clearCart(List cids) {
        cartDao.clearAllByCids(cids);
    }

    @Override
    public void setN(List cids) {
        cartDao.setN(cids);
    }

    @Override
    public void setY(List<Integer> cids) {
        cartDao.setY(cids);
    }

    @Override
    public List<Map<String, Object>> getBidNum(List<Integer> cids) {
        return cartDao.getBidNume(cids);
    }

    @Override
    public List<Cart> getByCids(List<Integer> cids) {
        return cartDao.getByCids(cids);
    }

    @Override
    public List<Cart> getRank() {
        return cartDao.getCartY();
    }
}
