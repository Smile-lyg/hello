package com.seecen.controller;

import com.seecen.dao.MenuDao;
import com.seecen.pojo.Menu;
import com.seecen.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/14
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuDao menuDao;

    @RequestMapping("/menus")
    @ResponseBody
    public MenuVo menus() throws SQLException, ClassNotFoundException {
        List<Menu> list = menuDao.menus();
        MenuVo menuVo = new MenuVo(list);
        menuVo.setCode(0);
        menuVo.setCount(0);
        menuVo.setMsg("msg");
        return menuVo;
    }

    @RequestMapping("/menuList")
    @ResponseBody
    public MenuVo menuList(int page, int limit) throws SQLException, ClassNotFoundException {
        MenuVo menuVo = new MenuVo();
        List<Menu> menus = menuDao.queryAll();

        int count = menus.size();
        if (count > 0) {
            int page_max = (int) Math.ceil((double) count / limit);
            page = page > page_max ? page_max : page;

            int start = (page - 1) * limit;
            int end = page * limit;

            // 截取要显示的数据
            List<Menu> data = menus.subList(start, end > count ? count : end);
            menuVo.setData(data);
        }
        menuVo.setMsg("msg");
        menuVo.setCode(0);
        menuVo.setCount(count);
        return menuVo;
    }

    @RequestMapping("/addMenu")
    @ResponseBody
    public String addMenu(Menu menu) throws SQLException, ClassNotFoundException {
        int i = menuDao.insert(menu);
        if (i >= 1) {
            return "添加成功";
        }else{
            return "添加失败";
        }
    }

    @RequestMapping("/deleteMenus")
    @ResponseBody
    public String deleteMenus(String ids) throws SQLException, ClassNotFoundException {
        ids = "(" + ids + ")";
        int i = menuDao.deleteMenusByIds(ids);
        if (i >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public String deleteMenu(int id) throws SQLException, ClassNotFoundException {
        int i = menuDao.deleteById(id);
        if (i >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping("/editMenu")
    @ResponseBody
    public String editMenu(Menu menu) throws SQLException, ClassNotFoundException {
        int i = menuDao.editMenu(menu);
        if (i >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping("/getMenus")
    @ResponseBody
    public MenuVo getMenus(Integer id, String title, int page, int limit) throws Exception {
        MenuVo menuVo;
        if (id == null) {
            menuVo = menuDao.getMenusByTitle(title);
        } else if (title.equals("")) {
            menuVo = menuDao.getMenusById(id);
        } else if (id != null && !title.equals("")) {
            menuVo = menuDao.getMenusByIdAndTitle(id, title);
        } else {
            menuVo = menuList(page, limit);
        }
        return menuVo;
    }

    @RequestMapping("/pList")
    @ResponseBody
    public MenuVo pList() throws SQLException, ClassNotFoundException {
        MenuVo menuVo = new MenuVo();
        List<Menu> menus = menuDao.queryP();
        menuVo.setData(menus);
        menuVo.setMsg("msg");
        menuVo.setCode(0);
        menuVo.setCount(menus.size());
        return menuVo;
    }

}
