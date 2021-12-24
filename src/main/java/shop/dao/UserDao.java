package shop.dao;

import shop.domain.User;

public interface UserDao {

    //根据用户名和密码查找用户
    User findByUsernameAndPassward(String username,String password);
    //管理员登录
    User adminlogin(String username, String password);
    //用户保存
    void save(User user) throws Exception;
    //通过用户名查询用户
    User findByUsername(String username);
    //通过激活码查询用户
    User findByCode(String code);
    //激活账户
    void updateStatus(User user);
}
