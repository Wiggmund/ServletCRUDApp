package org.example.servletcrudapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletcrudapp.model.User;
import org.example.servletcrudapp.service.UserService;
import org.example.servletcrudapp.service.impl.UserServiceImpl;
import org.example.servletcrudapp.db.DBConnectionDriverManager;
import org.example.servletcrudapp.repository.impl.UserRepositoryImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String AGE = "age";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
        this(new UserServiceImpl(
                new UserRepositoryImpl(
                        new DBConnectionDriverManager())));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            String id = req.getParameter(ID);
            if (id == null) {
                List<User> allUsers = userService.getAllUsers();
                allUsers.forEach(out::println);
            } else {
                userService.getUserById(Long.parseLong(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
