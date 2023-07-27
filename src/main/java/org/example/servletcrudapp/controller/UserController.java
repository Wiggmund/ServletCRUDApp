package org.example.servletcrudapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletcrudapp.db.DBConnectionDriverManager;
import org.example.servletcrudapp.repository.impl.UserRepositoryImpl;
import org.example.servletcrudapp.service.UserService;
import org.example.servletcrudapp.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    public UserController(){
        this(new UserServiceImpl(new UserRepositoryImpl(new DBConnectionDriverManager())));
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: 1) all users
        //userService.getAllUsers();
        //TODO: 2) user by id
        //TODO: for non existent id
        //userService.getUserById();

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: create
        //TODO: check whether all fields are supplied
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: update
        //TODO: no duplicates
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: delete
        //TODO: for non existent id
        PrintWriter writer = resp.getWriter();
        writer.println("line 1");
        writer.close();
        String userIdParam = req.getParameter("id");

        PrintWriter writer1 = resp.getWriter();

        if(userIdParam==null || userIdParam.isEmpty() ){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

           Long userId= Long.parseLong(userIdParam);

        userService.deleteUserById(userId);
    }
}
