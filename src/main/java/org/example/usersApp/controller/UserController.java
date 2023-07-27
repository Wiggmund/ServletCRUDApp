package org.example.usersApp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.usersApp.dto.CreateUserDto;
import org.example.usersApp.service.UserService;
import org.postgresql.util.LruCache;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    public UserController(){

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
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Integer age = Integer.valueOf(req.getParameter("age"));
        userService.createUser(new CreateUserDto(firstName, lastName, age));
        PrintWriter writer = resp.getWriter();
        writer.println("service invoked");


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
        userService.deleteUserById(1L);
        super.doDelete(req, resp);
    }
}
