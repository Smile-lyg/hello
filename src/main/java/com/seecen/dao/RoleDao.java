package com.seecen.dao;

import com.seecen.pojo.Car;
import com.seecen.pojo.Role;
import com.seecen.util.DBUtil;
import com.seecen.vo.CarVo;
import com.seecen.vo.RoleVo;
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
 * Date: 2020/11/14
 * Time: 10:35
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class RoleDao {

    public List<Role> queryAll() throws SQLException, ClassNotFoundException {
        List<Role> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_roles order by role";
        PreparedStatement prep = con.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Role role = new Role(
                    rs.getInt("role"),
                    rs.getString("explain")
            );
            list.add(role);
        }
        DBUtil.close(rs, prep, con);
        return list;
    }

    public int insert(Role role) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "insert into car_roles values(?, ?)";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, role.getRole());
        prep.setString(2, role.getExplain());
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int deleteRoles(String roles) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "delete from car_roles where role in " + roles;
        PreparedStatement prep = con.prepareStatement(sql);
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int deleteRole(int role) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "delete from car_roles where role = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, role);
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int editRole(Role role) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "update car_roles set explain = ? where role = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, role.getExplain());
        prep.setInt(2, role.getRole());
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public RoleVo getRolesByExplain(String explain) throws SQLException, ClassNotFoundException {
        List<Role> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_roles where explain like ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, "%" + explain + "%");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Role role = new Role(
                    rs.getInt("role"),
                    rs.getString("explain")
            );
            list.add(role);
        }
        RoleVo roleVo = new RoleVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return roleVo;
    }

    public RoleVo getRolesByRole(Integer role) throws SQLException, ClassNotFoundException {
        List<Role> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_roles where role = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, role);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Role r = new Role(
                    rs.getInt("role"),
                    rs.getString("explain")
            );
            list.add(r);
        }
        RoleVo roleVo = new RoleVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return roleVo;
    }

    public RoleVo getRolesByRoleAndExplain(Integer role, String explain) throws SQLException, ClassNotFoundException {
        List<Role> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_roles where role = ? and explain like ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, role);
        prep.setString(2, "%" + explain + "%");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Role r = new Role(
                    rs.getInt("role"),
                    rs.getString("explain")
            );
            list.add(r);
        }
        RoleVo roleVo = new RoleVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return roleVo;
    }

    public int getRoles(int role,String roles) throws SQLException, ClassNotFoundException {
        int i = 0;
        Connection con = DBUtil.getCon();
        String sql;
        PreparedStatement prep;
        if(role == -1){
            sql = "select * from car_users where role in " + roles;
            prep = con.prepareStatement(sql);
        }else {
            sql = "select * from car_users where role = ?";
            prep = con.prepareStatement(sql);
            prep.setInt(1, role);
        }
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            i = 1;
            break;
        }
        DBUtil.close(rs, prep, con);
        return i;
    }
}
