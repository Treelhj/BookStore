package shop.service.impl;

import shop.dao.UserDao;
import shop.dao.impl.UserDaoImpl;
import shop.domain.Book;
import shop.domain.PageBean;
import shop.domain.User;
import shop.service.UserService;
import shop.util.MailUtils;
import shop.util.Md5Util;
import shop.util.UuidUtil;

public class UserServiceImpl implements UserService{

    private UserDao userDao=new UserDaoImpl();

    //用户登录
    @Override
    public User login(User user) throws Exception {
        return userDao.findByUsernameAndPassward(user.getUsername(),Md5Util.encodeByMd5(user.getPassword()));
    }
    //管理员登录
    @Override
    public User alogin(User user) throws Exception {
            return userDao.adminlogin(user.getUsername(), Md5Util.encodeByMd5(user.getPassword()));
    }

    //用户注册
    @Override
    public boolean regist(User user) throws Exception {
        User u=userDao.findByUsername(user.getUsername());
        if(u!=null){
            return false;
        }
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        String s=Md5Util.encodeByMd5(user.getPassword());

        user.setPassword(s);
        userDao.save(user);

        //发送激活邮件
        String content="<a href='http://120.77.58.192:8080/shop/user/activeUser?code="+user.getCode()+"'>点击激活</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }
    //激活用户
    @Override
    public boolean active(String code) {
        User user=userDao.findByCode(code);
        if(user!=null){
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }

    }

}
