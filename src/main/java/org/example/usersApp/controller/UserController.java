package org.example.usersApp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.usersApp.db.DBConnectionDriverManager;
import org.example.usersApp.model.User;
import org.example.usersApp.repository.impl.UserRepositoryImpl;
import org.example.usersApp.service.UserService;
import org.example.usersApp.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
        this(new UserServiceImpl(
                new UserRepositoryImpl(
                        new DBConnectionDriverManager()
                )
        ));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //TODO: 1) all users
        userService.getAllUsers();

        //TODO: 2) user by id
        String action = req.getParameter("action");
        String userIdParam = req.getParameter("userId");
        if (userIdParam != null && !userIdParam.isEmpty()) {
            try {
                long userId = Integer.parseInt(userIdParam);
                Optional<User> user = userService.getUserById(userId);
                if (user.isPresent()) {
                    resp.setStatus(200);
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                    out.println("<p>Пользователь с ID " + userId);
                } else {
                    resp.setStatus(404);
                    out.println("<p>Пользователь с ID " + userId + " не найден</p>");
                }
            } catch (NumberFormatException e) {
                out.println("<p>Некорректный ID пользователя</p>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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
