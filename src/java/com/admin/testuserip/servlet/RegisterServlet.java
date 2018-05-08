/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.testuserip.servlet;

import com.admin.testuserip.dao.UserDao;
import com.admin.testuserip.dao.UserDaoImpl;
import com.admin.testuserip.entity.User;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            //获得请求中传来的用户名和密码
            String name = request.getParameter("UserName").trim();
            String psw = request.getParameter("Password").trim();
 
            User user = new User(); //实例化一个对象，组装属性  
            user.setName(name);  
            user.setPwd(psw);  
            user.setSex("");  
            user.setHome("");  
            user.setInfo(""); 
        
            JSONObject jsonObject = new JSONObject();          
            Map<String, String> obj = new HashMap<>();
            UserDao ud = new UserDaoImpl();   
            if(ud.register(user)){  
                jsonObject.put("success", true);
                jsonObject.put("msg", "注册成功");                   
                obj.put("usename", user.getName()); 
                obj.put("psw", user.getPwd()); 
                obj.put("sex", user.getSex()); 
                obj.put("home", user.getHome()); 
                obj.put("info", user.getInfo()); 
                jsonObject.put("obj", obj); 
            }else{  
                jsonObject.put("success", false);
                jsonObject.put("msg", "注册失败，用户名已存在！！！");                      
                jsonObject.put("obj", obj); 
            }                    
            out.write(jsonObject.toString());
        }    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
