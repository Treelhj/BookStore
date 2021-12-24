package shop.service;

import shop.domain.Book;
import shop.domain.PageBean;
import shop.domain.User;

public interface UserService {
    //用户登录
    User login(User user) throws Exception;

    //管理员登录
    User alogin(User user) throws Exception;

    //注册
    boolean regist(User user) throws Exception;

    //激活用户
    boolean active(String code);

}
