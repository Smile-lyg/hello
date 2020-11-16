package com.seecen.controller;

import com.seecen.dao.UserDao;
import com.seecen.pojo.User;
import com.seecen.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/9
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/login")
    public String login(String userName, String password) throws SQLException, ClassNotFoundException {
        int i = userDao.login(userName, password);
        if (i >= 1) {
            return "redirect:../resources/index.jsp";
        } else {
            return "redirect:../login.jsp";
        }
    }

    @RequestMapping("/userList")
    @ResponseBody
    public UserVo userList(int page, int limit) throws Exception {
        /**
         * 最后一页只有一条数据时，删掉最后一条数据后，请求页page没变，会出现异常
         * 正常情况：
         * page = 3, limit = 5, count = 11
         * start = (3 - 1) * 5 = 10, end = 3 * 5 = 15
         * users.subList(10, 11);
         * 异常情况：
         * page = 3, limit = 5, count = 10
         * start = (3 - 1) * 5 = 10, end = 3 * 5 = 15
         * users.subList(10, 10);
         * 如果只有10条数据，那么page > count / limit，
         * 但本应该page <= count / limit
         * 向上取整用Math.ceil(double a)
         * 向下取整用Math.floor(double a)
         */
        UserVo userVo = new UserVo();
        List<User> users = userDao.queryAll();

        int count = users.size();
        if (count > 0) {
            int page_max = (int) Math.ceil((double) count / limit);
            page = page > page_max ? page_max : page;

            int start = (page - 1) * limit;
            int end = page * limit;

            // 截取要显示的数据
            List<User> data = users.subList(start, end > count ? count : end);
            userVo.setData(data);
        }
        userVo.setMsg("msg");
        userVo.setCode(0);
        userVo.setCount(count);
        return userVo;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User user) throws SQLException, ClassNotFoundException {
        int i = userDao.insert(user);
        if (i >= 1) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(int id) throws SQLException, ClassNotFoundException {
        int i = userDao.deleteById(id);
        if (i >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public String editUser(User user) throws SQLException, ClassNotFoundException {
        int i = userDao.editUser(user);
        if (i >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping("/deleteUsers")
    @ResponseBody
    public String deleteUsers(String ids) throws SQLException, ClassNotFoundException {
        ids = "(" + ids + ")";
        int i = userDao.deleteUsersByIds(ids);
        if (i >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping("/getUsers")
    @ResponseBody
    public UserVo getUsers(Integer id, String username, int page, int limit) throws Exception {
        UserVo userVo = null;
        if (id == null) {
            userVo = userDao.getUsersByName(username);
        } else if (username.equals("")) {
            userVo = userDao.getUsersById(id);
        } else if (id != null && !username.equals("")) {
            userVo = userDao.getUsersByNameAndId(id, username);
        } else {
            userVo = userList(page, limit);
        }
        return userVo;
    }

    @RequestMapping("/getId")
    @ResponseBody
    public int getId() throws SQLException, ClassNotFoundException {
        int id = userDao.getId();
        return id;
    }
}
