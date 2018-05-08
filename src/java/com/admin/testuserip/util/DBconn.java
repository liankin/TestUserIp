/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.testuserip.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DBconn {
    //主机:端口号/数据库名(myfirstapp)
    static String url = "jdbc:mysql://localhost:3306/myfirstapp?characterEncoding=utf8";   
    static String username = "root";   
    static String password = "";   
    static Connection  conn = null;  
    static ResultSet rs = null;  
    static PreparedStatement ps =null;  
    public static void init(){  
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            conn = DriverManager.getConnection(url,username,password);  
        } catch (Exception e) {  
            System.out.println("init [SQL驱动程序初始化失败！]");  
            e.printStackTrace();  
        }  
    }  
    public static int addUpdDel(String sql){  
        int i = 0;  
        try {  
            PreparedStatement ps =  conn.prepareStatement(sql);  
            i =  ps.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println("sql数据库增删改异常");  
            e.printStackTrace();  
        }  
          
        return i;  
    }  
    public static ResultSet selectSql(String sql){  
        try {  
            ps =  conn.prepareStatement(sql);  
            rs =  ps.executeQuery(sql);  
        } catch (SQLException e) {  
            System.out.println("sql数据库查询异常");  
            e.printStackTrace();  
        }  
        return rs;  
    }  
    public static void closeConn(){  
        try {  
            conn.close();  
        } catch (SQLException e) {  
            System.out.println("sql数据库关闭异常");  
            e.printStackTrace();  
        }  
    }  
}
