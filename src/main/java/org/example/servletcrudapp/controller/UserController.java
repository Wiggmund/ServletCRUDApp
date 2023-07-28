package org.example.servletcrudapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.servletcrudapp.db.DBConnectionDriverManager;
import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.model.User;
import org.example.servletcrudapp.repository.impl.UserRepositoryImpl;
import org.example.servletcrudapp.service.UserService;
import org.example.servletcrudapp.service.impl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try (PrintWriter out = resp.getWriter()) {
            String id = req.getParameter(ID);

            if (id == null) {
                List<User> allUsers = userService.getAllUsers();
                allUsers.forEach(out::println);
            } else {
                User user = userService.getUserById(Long.parseLong(id));
                out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> parameters = parsePUTBody(req);

        try {
            User updatedUser = userService.updateUser(new UpdateUserDto(
                    Long.parseLong(parameters.get(ID)),
                    parameters.get(FIRST_NAME),
                    parameters.get(LAST_NAME),
                    Integer.parseInt(parameters.get(AGE))
            ));
            PrintWriter writer = resp.getWriter();
            writer.println(updatedUser);
            writer.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Integer age = Integer.valueOf(req.getParameter("age"));
        userService.createUser(new CreateUserDto(firstName, lastName, age));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdParam = parsePUTBody(req).get(ID);
        PrintWriter writer = resp.getWriter();

        if (userIdParam == null || userIdParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Long userId = Long.parseLong(userIdParam);

        try {
            userService.deleteUserById(userId);
        } catch (SQLException e) {
            writer.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> parsePUTBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        Map<String, String> parameters = new HashMap<>();
        String[] keyValuePairs = requestBody.toString().split("&");

        for (String keyValuePair : keyValuePairs) {
            String[] keyValue = keyValuePair.split("=");
            String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
            String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);

            parameters.put(key, value);
        }

        return parameters;
    }
}
