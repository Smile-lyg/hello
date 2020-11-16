package test;

import com.seecen.controller.UserController;
import com.seecen.dao.UserDao;
import com.seecen.pojo.User;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/13
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class TestGetId {
    @Test
    public void getId() throws SQLException, ClassNotFoundException {
        System.out.println(new UserDao().getId());
    }
}
