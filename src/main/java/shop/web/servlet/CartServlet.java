package shop.web.servlet;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import shop.domain.Cart;
import shop.domain.User;
import shop.service.CartService;
import shop.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet {

    private CartService cartService=new CartServiceImpl();

    //添加商品至购物车
    public void addCart(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String bidstr = request.getParameter("bid");
        int bid=Integer.parseInt(bidstr);
        String numstr = request.getParameter("num");
        int num=Integer.parseInt(numstr);
        User user = (User) request.getSession().getAttribute("user");
        String username=user.getUsername();
        int uid =user.getUid();
        System.out.println(bid+" "+num+" "+username+" "+uid);
        cartService.addCart(bid,uid,num,username);
    }

    //获取购物车详情
    public void getCart(HttpServletRequest request,HttpServletResponse response) throws IOException{
        User user = (User) request.getSession().getAttribute("user");
        int uid =user.getUid();
        List<Cart> carts=cartService.getCart(uid);
        writeValue(carts,response);
    }

    //删除购物车某件商品
    public void delCart(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String cidstr = request.getParameter("cid");
        int cid=Integer.parseInt(cidstr);
        cartService.delCart(cid);
    }
    //清空购物车
    public void clearCart(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String cidsstr = request.getParameter("cids");
//        System.out.println(cidsstr);
        ObjectMapper mapper = new ObjectMapper();
        List cids=mapper.readValue(cidsstr, ArrayList.class);

      /*  System.out.println(cids);

        String s="";
        StringBuilder sb=new StringBuilder(s);
        for(int i=0;i<cids.size();i++){
            System.out.println(cids.get(i));
            if(i==cids.size()-1){
                sb.append("'"+cids.get(i)+"'");
            }else{
                sb.append("'"+cids.get(i)+"',");
            }
        }
        System.out.println(sb.toString());*/

      cartService.clearCart(cids);

    }

    //获取商品售出数量的排序
    public void getRank(HttpServletRequest request,HttpServletResponse response) throws IOException{

        List<Cart> carts=cartService.getRank();
        writeValue(carts,response);
    }

}
