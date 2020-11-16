package com.seecen.dao;

import com.seecen.pojo.User;
import com.seecen.util.DBUtil;
import com.seecen.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/9
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDao {
    public int login(String userName, String password) throws SQLException, ClassNotFoundException {
        int i = 0;
        Connection con = DBUtil.getCon();
        String sql = "select * from car_users where username = ? and password = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, userName);
        prep.setString(2, password);
        ResultSet rs = prep.executeQuery();
        if (rs.next()) {
            i = 1;
        }
        DBUtil.close(rs, prep, con);
        return i;
    }

    public List<User> queryAll() throws SQLException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_users order by id desc";
        PreparedStatement prep = con.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("tel"),
                    rs.getInt("role")
            );
            list.add(user);
        }
        DBUtil.close(rs, prep, con);
        return list;
    }

    public int insert(User user) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "insert into car_users values(?, ?, ?, ?, ?)";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, user.getId());
        prep.setString(2, user.getUsername());
        prep.setString(3, user.getPassword());
        prep.setString(4, user.getTel());
        prep.setInt(5, user.getRole());
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int deleteById(int id) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "delete from car_users where id = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, id);
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int editUser(User user) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "update car_users set username = ?, password = ?, tel = ?, role = ? where id = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, user.getUsername());
        prep.setString(2, user.getPassword());
        prep.setString(3, user.getTel());
        prep.setInt(4, user.getRole());
        prep.setInt(5, user.getId());
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int deleteUsersByIds(String ids) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "delete from car_users where id in " + ids;
        PreparedStatement prep = con.prepareStatement(sql);
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public UserVo getUsersByName(String username) throws SQLException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_users where username like ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, "%" + username + "%");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("tel"),
                    rs.getInt("role")
            );
            list.add(user);
        }
        UserVo userVo = new UserVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return userVo;
    }

    public UserVo getUsersById(Integer id) throws SQLException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_users where id = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, id);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("tel"),
                    rs.getInt("role")
            );
            list.add(user);
        }
        UserVo userVo = new UserVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return userVo;
    }

    public UserVo getUsersByNameAndId(Integer id, String username) throws SQLException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_users where id = ? and username like ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, id);
        prep.setString(2, "%" + username + "%");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("tel"),
                    rs.getInt("role")
            );
            list.add(user);
        }
        UserVo userVo = new UserVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return userVo;
    }

    public int getId() throws SQLException, ClassNotFoundException {
        int id = 0;
        Connection con = DBUtil.getCon();
        String sql = "select seq_users.nextval from dual";
        PreparedStatement prep = con.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();
        while (rs.next()){
            id = rs.getInt(1);
        }
        DBUtil.close(rs, prep, con);
        return id;
    }
}
