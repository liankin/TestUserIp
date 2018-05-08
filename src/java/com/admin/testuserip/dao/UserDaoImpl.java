/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.testuserip.dao;

import com.admin.testuserip.entity.User;
import com.admin.testuserip.util.DBconn;
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.List;   
  
public class UserDaoImpl implements UserDao{  
      
    public boolean register(User user) {  
        boolean flag = false;        
        try {  
                DBconn.init();  
                ResultSet rs = DBconn.selectSql("select * from user where name='"+user.getName()+"'");
                if(rs.next()){
                    //此用户名已存在
                    flag = false;
                }else{
                    int i =DBconn.addUpdDel("insert into user(name,pwd,sex,home,info) " +  
                      "values('"+user.getName()+"','"+user.getPwd()+"','"+user.getSex()+"','"+user.getHome()+"','"+user.getInfo()+"')");  
                    if(i>0){  
                        flag = true;  
                    }
                }                 
                rs.close();
                DBconn.closeConn();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }          
        return flag;  
    }  
    public User login(String name, String pwd) {  
        User user = null;
        try {  
                DBconn.init();  
                ResultSet rs = DBconn.selectSql("select * from user where name='"+name+"' and pwd='"+pwd+"'");  
                while(rs.next()){  
                    user = new User();
                    user.setId(rs.getInt("id"));  
                    user.setName(rs.getString("name"));  
                    user.setPwd(rs.getString("pwd"));  
                    user.setSex(rs.getString("sex"));  
                    user.setHome(rs.getString("home"));  
                    user.setInfo(rs.getString("info"));  
                }  
                rs.close();
                DBconn.closeConn();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return user;  
    }     
    
    public List<User> getUserAll() {  
        List<User> list = new ArrayList<User>();  
        try {  
            DBconn.init();  
            ResultSet rs = DBconn.selectSql("select * from user");  
            while(rs.next()){  
                User user = new User();  
                user.setId(rs.getInt("id"));  
                user.setName(rs.getString("name"));  
                user.setPwd(rs.getString("pwd"));  
                user.setSex(rs.getString("sex"));  
                user.setHome(rs.getString("home"));  
                user.setInfo(rs.getString("info"));  
                list.add(user);  
            }  
            rs.close();
            DBconn.closeConn();  
            return list;  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    public boolean update(int id,String name, String pwd,String sex, String home,String info) {  
        boolean flag = false;  
        DBconn.init();  
        String sql ="update user set name ='"+name  
                +"' , pwd ='"+pwd  
                +"' , sex ='"+sex  
                +"' , home ='"+home  
                +"' , info ='"+info+"' where id = "+id;  
        int i =DBconn.addUpdDel(sql);  
        if(i>0){  
            flag = true;  
        }  
        DBconn.closeConn();  
        return flag;  
    }  
    public boolean delete(int id) {  
        boolean flag = false;  
        DBconn.init();  
        String sql = "delete  from user where id="+id;  
        int i =DBconn.addUpdDel(sql);  
        if(i>0){  
            flag = true;  
        }  
        DBconn.closeConn();  
        return flag;  
    }  
      
}  
