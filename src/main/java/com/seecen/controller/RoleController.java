package com.seecen.controller;

import com.seecen.dao.RoleDao;
import com.seecen.dao.UserDao;
import com.seecen.pojo.Role;
import com.seecen.vo.RoleVo;
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
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleDao roleDao;

    // 获取全部
    @RequestMapping("/roleList")
    @ResponseBody
    public RoleVo roleList(int page, int limit) throws SQLException, ClassNotFoundException {
        RoleVo roleVo = new RoleVo();
        List<Role> roles = roleDao.queryAll();
        int count = roles.size();
        if (count > 0) {
            int page_max = (int) Math.ceil((double) count / limit);
            page = page > page_max ? page_max : page;

            int start = (page - 1) * limit;
            int end = page * limit;

            // 截取要显示的数据
            List<Role> data = roles.subList(start, end > count ? count : end);
            roleVo.setData(data);
        }
        if (limit == 0) { // 用户管理页获取全部角色数据
            roleVo.setData(roles);
        }
        roleVo.setMsg("msg");
        roleVo.setCode(0);
        roleVo.setCount(count);
        return roleVo;
    }

    // 添加
    @RequestMapping("/addRole")
    @ResponseBody
    public String addRole(Role role) throws SQLException, ClassNotFoundException {
        int i = roleDao.insert(role);
        if (i >= 1) {
            return "添加成功";
        }else{
            return "添加失败";
        }
    }

    // 多个删除
    @RequestMapping("/deleteRoles")
    @ResponseBody
    public String deleteRoles(String roles) throws SQLException, ClassNotFoundException {
        roles = "(" + roles + ")";
        int i = roleDao.getRoles(-1, roles);
        if (i >= 1) {
            return "存在已被使用的角色信息，删除失败";
        } else {
            i = roleDao.deleteRoles(roles);
            if (i >= 1) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        }
    }

    // 单个删除
    @RequestMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(int role) throws SQLException, ClassNotFoundException {
        int i = roleDao.getRoles(role, null);
        if (i >= 1) {
            return "该角色信息已被使用，不可删除";
        } else {
            i = roleDao.deleteRole(role);
            if (i >= 1) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        }
    }

    // 编辑
    @RequestMapping("/editRole")
    @ResponseBody
    public String editRole(Role role) throws SQLException, ClassNotFoundException {
        int i = roleDao.editRole(role);
        if (i >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    // 查询
    @RequestMapping("/getRoles")
    @ResponseBody
    public RoleVo getRoles(Integer role,String explain, int page, int limit) throws SQLException, ClassNotFoundException {
        RoleVo roleVo;
        if (role==null) {
            roleVo = roleDao.getRolesByExplain(explain);
        } else if (explain.equals("")) {
            roleVo = roleDao.getRolesByRole(role);
        } else if (role!=null && !explain.equals("")) {
            roleVo = roleDao.getRolesByRoleAndExplain(role, explain);
        } else {
            roleVo = roleList(page, limit);
        }
        return roleVo;
    }

}
