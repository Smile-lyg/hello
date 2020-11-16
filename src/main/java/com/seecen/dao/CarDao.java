package com.seecen.dao;

import com.seecen.pojo.Car;
import com.seecen.util.DBUtil;
import com.seecen.vo.CarVo;
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
 * Date: 2020/11/13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CarDao {

    public List<Car> queryAll() throws SQLException, ClassNotFoundException {
        List<Car> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_cars order by id desc";
        PreparedStatement prep = con.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Car car = new Car(
                    rs.getInt("id"),
                    rs.getString("carNumber"),
                    rs.getString("type"),
                    rs.getInt("isNew"),
                    rs.getInt("isRepair")
            );
            list.add(car);
        }
        DBUtil.close(rs, prep, con);
        return list;
    }

    public int insert(Car car) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "insert into car_cars values(?, ?, ?, ?, ?)";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, car.getId());
        prep.setString(2, car.getCarNumber());
        prep.setString(3, car.getType());
        prep.setInt(4, car.getIsNew());
        prep.setInt(5, car.getIsRepair());
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int deleteCarsByIds(String ids) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "delete from car_cars where id in " + ids;
        PreparedStatement prep = con.prepareStatement(sql);
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int deleteById(int id) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "delete from car_cars where id = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setInt(1, id);
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int editCar(Car car) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getCon();
        String sql = "update car_cars set carNumber = ?, type = ?, isNew = ?, isRepair = ? where id = ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, car.getCarNumber());
        prep.setString(2, car.getType());
        prep.setInt(3, car.getIsNew());
        prep.setInt(4, car.getIsRepair());
        prep.setInt(5, car.getId());
        int i = prep.executeUpdate();
        DBUtil.close(prep, con);
        return i;
    }

    public int getId() throws SQLException, ClassNotFoundException {
        int id = 0;
        Connection con = DBUtil.getCon();
        String sql = "select seq_cars.nextval from dual";
        PreparedStatement prep = con.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            id = rs.getInt(1);
        }
        DBUtil.close(rs, prep, con);
        return id;
    }

    public CarVo getCarsByType(String type) throws SQLException, ClassNotFoundException {
        List<Car> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_cars where type like ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, "%" + type + "%");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Car car = new Car(
                    rs.getInt("id"),
                    rs.getString("carNumber"),
                    rs.getString("type"),
                    rs.getInt("isNew"),
                    rs.getInt("isRepair")
            );
            list.add(car);
        }
        CarVo carVo = new CarVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return carVo;
    }

    public CarVo getCarsByNumber(String carNumber) throws SQLException, ClassNotFoundException {
        List<Car> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_cars where carNumber like ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, "%" + carNumber + "%");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Car car = new Car(
                    rs.getInt("id"),
                    rs.getString("carNumber"),
                    rs.getString("type"),
                    rs.getInt("isNew"),
                    rs.getInt("isRepair")
            );
            list.add(car);
        }
        CarVo carVo = new CarVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return carVo;
    }

    public CarVo getCarsByNumberAndType(String carNumber, String type) throws SQLException, ClassNotFoundException {
        List<Car> list = new ArrayList<>();
        Connection con = DBUtil.getCon();
        String sql = "select * from car_cars where carNumber like ? and type like ?";
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, "%" + carNumber + "%");
        prep.setString(2, "%" + type + "%");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            Car car = new Car(
                    rs.getInt("id"),
                    rs.getString("carNumber"),
                    rs.getString("type"),
                    rs.getInt("isNew"),
                    rs.getInt("isRepair")
            );
            list.add(car);
        }
        CarVo carVo = new CarVo(0,list.size(),"",list);
        DBUtil.close(rs,prep,con);
        return carVo;
    }
}
