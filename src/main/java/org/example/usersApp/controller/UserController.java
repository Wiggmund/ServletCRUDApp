package org.example.usersApp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.usersApp.service.UserService;

import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
        userService.deleteUserById(1L);
        super.doDelete(req, resp);
    }
}
