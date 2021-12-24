package shop.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import shop.domain.Book;
import shop.domain.Cart;
import shop.domain.Order;
import shop.domain.User;
import shop.service.BookService;
import shop.service.CartService;
import shop.service.OrderService;
import shop.service.impl.BookServiceImpl;
import shop.service.impl.CartServiceImpl;
import shop.service.impl.OrderServiceImpl;
import shop.util.CodeUtil;
import shop.util.JDBCUtils;
import shop.util.UuidUtil;
import sun.misc.IOUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {

    private OrderService orderService=new OrderServiceImpl();
    private CartService cartService=new CartServiceImpl();
    private BookService bookService=new BookServiceImpl();

    //添加订单
    public void addOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {

        User user = (User)request.getSession().getAttribute("user");
        String username=user.getUsername();
        String cidsstr = request.getParameter("cids");
        String name = request.getParameter("people");
        String telephone = request.getParameter("telephone");
        String address = request.getParameter("address");
        String pricestr = request.getParameter("price");
        double price=Double.parseDouble(pricestr);
        ObjectMapper mapper = new ObjectMapper();
        List cids=mapper.readValue(cidsstr, ArrayList.class);

      /*  System.out.println(name);
        System.out.println(telephone);
        System.out.println(address);
        System.out.println(price);
        for (Object cid : cids) {
            System.out.println(cid);
        }*/

        Order order=new Order();
        String oid= UuidUtil.getUuid();
        order.setOid(oid);
        order.setUsername(username);
        order.setName(name);
        order.setAddress(address);
        order.setTelephone(telephone);
        order.setPrice(price);
        orderService.addOrder(order);//添加订单

        orderService.setCartOrder(cids,oid);

        cartService.setN(cids);//设置订单中每样商品为未支付 N


        //返回订单后，选择立即支付就跳转到支付页面（以订单号为唯一标识符）
        writeValue(oid,response);

    }

    //设置订单为已支付
    public void setOrderY(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //设置订单为已支付 Y ，并设置订单中每样商品为已支付 Y
        String oid = request.getParameter("oid");
        orderService.setOrderY(oid);
        //获取cids
        List<Integer> cids=orderService.getCidsByOid(oid);

        for (Integer cid : cids) {
            System.out.println(cid);
        }
        //设置cart中cid为Y
        cartService.setY(cids);

        //更新book的库存
        //先找Bid num Y
        List<Map<String, Object>> lists=cartService.getBidNum(cids);

        /*for (Map<String, Object> list : lists) {
            System.out.println( list.get("bid")+"  "+ list.get("num"));
        }*/
        bookService.newNum(lists);



        /*JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String oid = request.getParameter("oid");
        System.out.println("修改："+oid);
        String sql="update orders set status = 'Y' where oid = ? ";
        template.update(sql,oid);
        System.out.println("修改成功");*/

    }

    //查询订单支付状态
    public void getOStatus(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //只是查询订单的支付状态
        String oid = request.getParameter("oid");
        String status=orderService.getOStatus(oid);
        writeValue(status,response);
       /* JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String oid = request.getParameter("oid");
        System.out.println(oid);
        String sql="select * from orders where oid = ? ";
        Order order= template.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class), oid);
        String flag=order.getStatus();
        System.out.println(flag);
        writeValue(flag,response);*/
    }

    //获取用户所有订单
    public void getOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {

        User user = (User)request.getSession().getAttribute("user");
        String username=user.getUsername();
        List<Order> orders=orderService.getOrders(username);
        writeValue(orders,response);

    }
    //获取所有用户所有订单
    public void getAllOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {

        List<Order> orders=orderService.getAllOrder();
        writeValue(orders,response);

    }

    //删除订单
    public void delOrder(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String oid = request.getParameter("oid");
        //获取cids
        List<Integer> cids=orderService.getCidsByOid(oid);
        //删除cart中对应的cid

        cartService.clearCart(cids);

        //删除cart_orders对应oid的行
        orderService.delCidOid(oid);

        //删除orders对应的oid
        orderService.delOrder(oid);
    }

    //获取订单详情
    public void getDetail(HttpServletRequest request,HttpServletResponse response) throws IOException {

        //获取oid对应的cid
        String oid = request.getParameter("oid");
        List<Integer> cids=orderService.getCidsByOid(oid);

        //查询cid返回carts
        List<Cart> carts=cartService.getByCids(cids);
       /* for (Cart cart : carts) {
            System.out.println(cart);
        }*/
        writeValue(carts,response);

    }



}
