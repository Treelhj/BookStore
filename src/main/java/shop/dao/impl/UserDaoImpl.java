package shop.dao.impl;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import shop.dao.UserDao;
import shop.domain.User;
import shop.util.JDBCUtils;
import shop.util.Md5Util;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    //根据用户名和密码查找用户
    @Override
    public User findByUsernameAndPassward(String username, String password) {
        User user=null;
        try {
            String sql="select * from user where username = ? and password = ?";
            user=template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
        } catch (Exception e) {
        }
        return user;
    }
    //管理员登录
    @Override
    public User adminlogin(String username, String password) {
        User user=null;
        try {
            String sql="select * from admin where username = ? and password = ?";
            user=template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
        } catch (Exception e) {
        }
        return user;
    }
    //用户保存
    @Override
    public void save(User user){
        String sql="insert into user(username,password,email,status,code) values(?,?,?,?,?)";
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }
    //通过用户名查询用户
    @Override
    public User findByUsername(String username) {

        User user=null;
        try {
            String sql="select * from user where username = ? ";
            user=template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
        } catch (Exception e) {
        }
        return user;
    }

    //通过激活码查询用户
    @Override
    public User findByCode(String code) {
        User user=null;
        try {
            String sql="select * from user where code = ? ";
            user=template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),code);
        } catch (Exception e) {
        }
        return user;
    }
    //激活账户
    @Override
    public void updateStatus(User user) {
        String sql="update user set status = 'Y' where uid = ?";
        template.update(sql,user.getUid());
    }
}
