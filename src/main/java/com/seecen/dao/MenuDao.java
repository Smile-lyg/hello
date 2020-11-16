package com.seecen.dao;

import com.seecen.pojo.Menu;
import com.seecen.util.DBUtil;
import com.seecen.vo.MenuVo;
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
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MenuDao {
    public List<Menu> menus() throws SQLException, ClassNotFoundException {
        List<Menu> navs = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql_p = "select * from car_menus where parentId = 0";
        String sql_c = "select * from car_menus where parentId = ?";
        PreparedStatement prep_p = con.prepareStatement(sql_p);
        PreparedStatement prep_c = con.prepareStatement(sql_c);
        ResultSet rs_c;
        ResultSet rs_p = prep_p.executeQuery();
        while (rs_p.next()) {
            int id = rs_p.getInt("id");
            Menu menu_p = new Menu(
                    id,
                    rs_p.getString("title"),
                    rs_p.getString("icon"),
                    rs_p.getString("href"),
                    rs_p.getInt("spread"),
                    rs_p.getInt("parentId")
            );
            prep_c.setInt(1,id);
            rs_c = prep_c.executeQuery();
            List<Menu> children = new ArrayList<>();
            while (rs_c.next()) {
                Menu menu_c = new Menu(
                        rs_c.getInt("id"),
                        rs_c.getString("title"),
                        rs_c.getString("icon"),
                        rs_c.getString("href"),
                        rs_c.getInt("spread"),
                        rs_c.getInt("parentId")
                );
                children.add(menu_c);
            }
            DBUtil.close(rs_c);
            menu_p.setChildren(children);
            navs.add(menu_p);
        }
        DBUtil.close(rs_p, prep_c, prep_p, con);
        return navs;
    }

    public List<Menu> queryAll() throws SQLException, ClassNotFoundException {
        List<Menu> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_menus order by id";
        PreparedStatement prep = con.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Menu menu = new Menu(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("icon"),
                    rs.getString("href"),
                    rs.getInt("spread"),
                    rs.getInt("parentId")
            );
            list.add(menu);
        }
        DBUtil.close(rs, prep, con);
        return list;
    }

    public int insert(Menu menu) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "insert into car_menus values(?, ?, ?, ?, ?, ?)";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, menu.getId());
        prep.setString(2, menu.getTitle());
        prep.setString(3, menu.getIcon());
        if (menu.getHref().equals("")) {
            prep.setString(4, "page/404.html");
        } else {
            prep.setString(4, menu.getHref());
        }
        prep.setInt(5, menu.getSpread());
        prep.setInt(6, menu.getParentId());
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int deleteMenusByIds(String ids) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "delete from car_menus where id in " + ids;
        PreparedStatement prep = con.prepareStatement(sql);
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int deleteById(int id) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "delete from car_menus where id = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, id);
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int editMenu(Menu menu) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "update car_menus set title = ?, icon = ?, href = ?, spread = ?, parentId = ? where id = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, menu.getTitle());
        prep.setString(2, menu.getIcon());
        if (menu.getHref().equals("")) {
            prep.setString(3, "page/404.html");
        } else {
            prep.setString(3, menu.getHref());
        }
        prep.setInt(4, menu.getSpread());
        prep.setInt(5, menu.getParentId());
        prep.setInt(6, menu.getId());
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public MenuVo getMenusById(Integer id) throws SQLException, ClassNotFoundException {
        List<Menu> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_menus where id = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, id);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Menu menu = new Menu(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("icon"),
                    rs.getString("href"),
                    rs.getInt("spread"),
                    rs.getInt("parentId")
            );
            list.add(menu);
        }
        MenuVo menuVo = new MenuVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return menuVo;
    }

    public MenuVo getMenusByTitle(String title) throws SQLException, ClassNotFoundException {
        List<Menu> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_menus where title like ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, "%" + title + "%");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Menu menu = new Menu(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("icon"),
                    rs.getString("href"),
                    rs.getInt("spread"),
                    rs.getInt("parentId")
            );
            list.add(menu);
        }
        MenuVo menuVo = new MenuVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return menuVo;
    }

    public MenuVo getMenusByIdAndTitle(Integer id, String title) throws SQLException, ClassNotFoundException {
        List<Menu> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_menus where id = ? and title like ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, id);
        prep.setString(2, "%" + title + "%");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Menu menu = new Menu(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("icon"),
                    rs.getString("href"),
                    rs.getInt("spread"),
                    rs.getInt("parentId")
            );
            list.add(menu);
        }
        MenuVo menuVo = new MenuVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return menuVo;
    }

    public List<Menu> queryP() throws SQLException, ClassNotFoundException {
        List<Menu> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_menus where parentId = 0 order by id";
        PreparedStatement prep = con.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Menu menu = new Menu(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("icon"),
                    rs.getString("href"),
                    rs.getInt("spread"),
                    rs.getInt("parentId")
            );
            list.add(menu);
        }
        DBUtil.close(rs, prep, con);
        return list;
    }
}
