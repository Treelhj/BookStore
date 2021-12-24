package shop.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import shop.domain.Book;
import shop.domain.PageBean;
import shop.service.BookService;
import shop.service.impl.BookServiceImpl;
import shop.util.UuidUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/book/*")
public class BookServlet extends BaseServlet {

    private BookService bookService=new BookServiceImpl();

    //分页查询书籍
    public void pageQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String bword = request.getParameter("bword");
        String currentPageStr = request.getParameter("currentPage");
        bword=new String(bword.getBytes("iso-8859-1"),"utf-8");



        int currentPage=0;//当前页码
        if(currentPageStr != null && currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else {
            currentPage=1;
        }
        PageBean<Book> pb = bookService.pageQuery(currentPage, bword);
        writeValue(pb,response);

    }

    //根据bid查询书籍
    public void findOne(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String bid = request.getParameter("bid");
        Book book=bookService.findByBid(bid);
        writeValue(book,response);
    }

    public void getBook(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Book> books =bookService.getAllBook();
        writeValue(books,response);
    }
    public void setN(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String bidstr = request.getParameter("bid");
        int bid=Integer.parseInt(bidstr);
        bookService.setN(bid);
    }

    //增加书籍
    public void addBook(HttpServletRequest request,HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DiskFileItemFactory sf= new DiskFileItemFactory();//实例化磁盘被文件列表工厂
        String path = request.getSession().getServletContext().getRealPath("/image");//得到上传文件的存放目录
        sf.setRepository(new File(path));//设置文件存放目录
        sf.setSizeThreshold(1024*1024*10);//设置文件上传小于10M放在内存中
        String renameImg = "";//文件新生成的文件名
        String fileName = "";//文件原名称

        Map<String,String> map=new HashMap<String, String>();

//        String name = "";//普通field字段
        //从工厂得到servletupload文件上传类
        ServletFileUpload sfu = new ServletFileUpload(sf);

        try {
            List<FileItem> lst = sfu.parseRequest(request);//得到request中所有的元素
            for (FileItem fileItem : lst) {
                if(fileItem.isFormField()){

                    String name=fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
//                    System.out.println(name+"  "+value);
                    map.put(name,value);

                }else{
                    //获得文件名称
                    fileName = fileItem.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                    String houzhui = fileName.substring(fileName.lastIndexOf("."));
                    renameImg = UuidUtil.getUuid()+houzhui;
//                    fileItem.write(new File(path, rename));
                    org.apache.commons.io.IOUtils.copy(fileItem.getInputStream(),new FileOutputStream(new File(path, renameImg)));
//                    fileItem.delete();
                    map.put("bimgid",renameImg);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        System.out.println(request.getContextPath());
        out.flush();
        out.close();
        Book book=new Book();
        try {
            BeanUtils.populate(book,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(book);
        bookService.addBook(book);

    }


    //修改书籍
    public void updateBook(HttpServletRequest request,HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DiskFileItemFactory sf= new DiskFileItemFactory();//实例化磁盘被文件列表工厂
        String path = request.getSession().getServletContext().getRealPath("/image");//得到上传文件的存放目录
        sf.setRepository(new File(path));//设置文件存放目录
        sf.setSizeThreshold(1024*1024*10);//设置文件上传小于10M放在内存中
        String renameImg = "";//文件新生成的文件名
        String fileName = "";//文件原名称

        Map<String,String> map=new HashMap<String, String>();

//        String name = "";//普通field字段
        //从工厂得到servletupload文件上传类
        ServletFileUpload sfu = new ServletFileUpload(sf);

        try {
            List<FileItem> lst = sfu.parseRequest(request);//得到request中所有的元素
            for (FileItem fileItem : lst) {
                if(fileItem.isFormField()){

                    String name=fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
//                    System.out.println(name+"  "+value);
                    map.put(name,value);

                }else{
                    //获得文件名称
                    fileName = fileItem.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                    String houzhui = fileName.substring(fileName.lastIndexOf("."));
                    renameImg = UuidUtil.getUuid()+houzhui;
//                    fileItem.write(new File(path, rename));
                    org.apache.commons.io.IOUtils.copy(fileItem.getInputStream(),new FileOutputStream(new File(path, renameImg)));
//                    fileItem.delete();
                    map.put("bimgid",renameImg);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        System.out.println(request.getContextPath());
        out.flush();
        out.close();
        Book book=new Book();
        try {
            BeanUtils.populate(book,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(book);
        bookService.updateBook(book);

    }





}
