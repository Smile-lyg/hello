package com.seecen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: zzw
 * Date: 2020/11/2
 * Time: 14:47
 * To change this template use File | Settings | File Templates.
 */
public class DBUtil {
    // 封装的数据库连接的方法
    public static Connection getCon() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "xmsc", "111111"
        );
        return con;
    }

    // 关闭资源的方法
    public static void close(AutoCloseable... closeables) {
        /**
         * 在传入的参数类型一致，但是不确定传入参数个数的时候
         * 可以使用可变参数
         * 在方法内部通过遍历的方式使用传递参数
         */
        for (AutoCloseable closeable : closeables) {
            if(closeable!=null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
