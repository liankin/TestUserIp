/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.testuserip.dao;

import com.admin.testuserip.entity.User;
import java.util.List;

/**
 *
 * @author admin
 */
public interface UserDao {
    public User login(String name,String pwd);//登录  
    public boolean register(User user);//注册  
    public List<User> getUserAll();//返回用户信息集合  
    public boolean delete(int id) ;//根据id删除用户  
    public boolean update(int id,String name, String pwd,String sex, String home,String info) ;//更新用户信息  
}
