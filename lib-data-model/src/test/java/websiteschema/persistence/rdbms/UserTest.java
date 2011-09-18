package websiteschema.persistence.rdbms;

import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import websiteschema.model.domain.PageInfo;
import websiteschema.model.domain.User;
import websiteschema.utils.MD5;

public class UserTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-beans.xml");
    UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);

    @Test
    public void insert() {
        User user = new User();
        user.setId("1");
        user.setName("name");
        user.setPasswd(MD5.getMD5("1".getBytes()));
        user.setEmail("1@1.com");
        user.setRole("ROLE_USER");

        userMapper.insert(user);

        selectAndUpdate();

        delete();
    }

    public void selectAndUpdate() {
        List<User> users = userMapper.getUsers(new PageInfo(0,2));
        for(User u: users) {
            System.out.println("username ------ "+u.getName());
        }

        User user = userMapper.getUserById("1");

        user.setPasswd("21232f297a57a5a743894a0e4a801fc3");

        userMapper.update(user);
    }

    public void delete() {
        userMapper.deleteById("1");
    }
}
