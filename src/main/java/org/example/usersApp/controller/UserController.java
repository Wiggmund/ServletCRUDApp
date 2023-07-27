package org.example.usersApp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.usersApp.db.DBConnectionDriverManager;
import org.example.usersApp.dto.UpdateUserDto;
import org.example.usersApp.model.User;
import org.example.usersApp.repository.impl.UserRepositoryImpl;
import org.example.usersApp.service.UserService;
import org.example.usersApp.service.impl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/user")
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
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: update
        //TODO: no duplicates
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: delete
        //TODO: for non existent id
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
