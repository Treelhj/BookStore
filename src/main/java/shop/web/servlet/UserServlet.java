package shop.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import shop.domain.ResultInfo;
import shop.domain.User;
import shop.service.UserService;
import shop.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();

    //用户登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String checkcode=request.getParameter("checkcode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(checkcode)){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            writeValue(info,response);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User u = null;
        try {
            u = service.login(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResultInfo info=new ResultInfo();

        if(u==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }
        if(u!=null&& !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("用户未激活！");
        }
        if(u!=null && "Y".equals(u.getStatus())){
            info.setFlag(true);
            request.getSession().setAttribute("user",u);
        }

        writeValue(info,response);
    }
    //管理员登录
    public void alogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String checkcode=request.getParameter("checkcode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(checkcode)){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            writeValue(info,response);
            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService service=new UserServiceImpl();
        User u = null;
        try {
            u = service.alogin(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ResultInfo info=new ResultInfo();

        if(u==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }

        if(u!=null){
            info.setFlag(true);
            request.getSession().setAttribute("admin",u);
        }

        writeValue(info,response);

    }
    //获取当前用户
    public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User u= (User) request.getSession().getAttribute("user");
        writeValue(u,response);
    }
    //获取当前管理员
    public void getAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User u= (User) request.getSession().getAttribute("admin");
        writeValue(u,response);
    }

    //退出登录
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");

    }

    //用户注册
    public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String checkcode=request.getParameter("checkcode");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(checkcode)){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            writeValue(info,response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserService service=new UserServiceImpl();
        boolean flag=false;
        try {
            flag = service.regist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResultInfo info=new ResultInfo();
        if(flag){
            info.setFlag(true);
        }else {
            info.setData(false);
            info.setErrorMsg("用户名已存在！");
        }

       writeValue(info,response);
    }

    //激活用户
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            //2.调用service完成激活
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            //3.判断标记
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href='../login.html'>登录</a>";
            } else {
                //激活失败
                msg = "激活失败，请联系管理员！";
            }

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
