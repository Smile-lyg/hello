package com.seecen.controller;

import com.seecen.dao.CarDao;
import com.seecen.pojo.Car;
import com.seecen.vo.CarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarDao carDao;
    
    @RequestMapping("/carList")
    @ResponseBody
    public CarVo carList(int page, int limit) throws SQLException, ClassNotFoundException {
        CarVo carVo = new CarVo();
        List<Car> cars = carDao.queryAll();

        int count = cars.size();
        if (count > 0) {
            int page_max = (int) Math.ceil((double) count / limit);
            page = page > page_max ? page_max : page;

            int start = (page - 1) * limit;
            int end = page * limit;

            // 截取要显示的数据
            List<Car> data = cars.subList(start, end > count ? count : end);
            carVo.setData(data);
        }
        carVo.setMsg("msg");
        carVo.setCode(0);
        carVo.setCount(count);
        return carVo;
    }

    @RequestMapping("/addCar")
    @ResponseBody
    public String addCar(Car car) throws SQLException, ClassNotFoundException {
        int i = carDao.insert(car);
        if (i >= 1) {
            return "添加成功";
        }else{
            return "添加失败";
        }
    }

    @RequestMapping("/deleteCars")
    @ResponseBody
    public String deleteUsers(String ids) throws SQLException, ClassNotFoundException {
        ids = "(" + ids + ")";
        int i = carDao.deleteCarsByIds(ids);
        if (i >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping("/deleteCar")
    @ResponseBody
    public String deleteCar(int id) throws SQLException, ClassNotFoundException {
        int i = carDao.deleteById(id);
        if (i >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping("/editCar")
    @ResponseBody
    public String editCar(Car car) throws SQLException, ClassNotFoundException {
        int i = carDao.editCar(car);
        if (i >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping("/getId")
    @ResponseBody
    public int getId() throws SQLException, ClassNotFoundException {
        int id = carDao.getId();
        return id;
    }
    
    @RequestMapping("/getCars")
    @ResponseBody
    public CarVo getUsers(String carNumber, String type, int page, int limit) throws Exception {
        CarVo carVo;
        if (carNumber.equals("")) {
            carVo = carDao.getCarsByType(type);
        } else if (type.equals("")) {
            carVo = carDao.getCarsByNumber(carNumber);
        } else if (!carNumber.equals("") && !type.equals("")) {
            carVo = carDao.getCarsByNumberAndType(carNumber, type);
        } else {
            carVo = carList(page, limit);
        }
        return carVo;
    }

}
