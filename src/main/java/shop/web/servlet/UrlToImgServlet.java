package shop.web.servlet;

import shop.util.CodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;

@WebServlet("/urlToImg")
public class UrlToImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将网址转为二维码
        String oid = request.getParameter("oid");
        String ip="";//主机ip地址
        CodeUtil.creatCode("http://"+ip+"/shop/payment_ok.html?oid="+oid, 200,200,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
