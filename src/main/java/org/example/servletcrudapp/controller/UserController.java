package org.example.servletcrudapp.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servletcrudapp.db.DBConnectionDriverManager;
import org.example.servletcrudapp.dto.CreateUserDto;
import org.example.servletcrudapp.dto.UpdateUserDto;
import org.example.servletcrudapp.exception.ExceptionResponse;
import org.example.servletcrudapp.exception.GlobalExceptionHandler;
import org.example.servletcrudapp.model.User;
import org.example.servletcrudapp.repository.impl.UserRepositoryImpl;
import org.example.servletcrudapp.service.UserService;
import org.example.servletcrudapp.service.impl.ControllerHelperService;
import org.example.servletcrudapp.service.impl.DuplicationService;
import org.example.servletcrudapp.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String AGE = "age";
    private static final String USERS_LIST_JSP = "/users.jsp";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
        this(new UserServiceImpl(
                new UserRepositoryImpl(new DBConnectionDriverManager()),
                new DuplicationService(new UserRepositoryImpl(new DBConnectionDriverManager()))));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        try {
            String id = req.getParameter(ID);

            if (id == null) {
                List<User> allUsers = userService.getAllUsers();
                req.setAttribute("usersList", allUsers);
                servletContext.getRequestDispatcher(USERS_LIST_JSP).forward(req, resp);
            } else {
                User user = userService.getUserById(Long.parseLong(id));
            }
        } catch (RuntimeException exception) {
            renderException(exception, req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String> parameters = ControllerHelperService.parseRequestBody(req);

            userService.updateUser(new UpdateUserDto(
                    Long.parseLong(parameters.get(ID)),
                    parameters.get(FIRST_NAME),
                    parameters.get(LAST_NAME),
                    Integer.parseInt(parameters.get(AGE))));
        } catch (RuntimeException exception) {
            renderException(exception, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            Integer age = Integer.valueOf(req.getParameter("age"));
            userService.createUser(new CreateUserDto(firstName, lastName, age));
        } catch (RuntimeException exception) {
            renderException(exception, req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/userCreate.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userIdParam = ControllerHelperService.parseRequestBody(req).get(ID);

            if (userIdParam == null || userIdParam.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Long userId = Long.parseLong(userIdParam);
            userService.deleteUserById(userId);
        } catch (RuntimeException exception) {
            renderException(exception, req, resp);
        }
    }

    private void renderException(RuntimeException exception, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        ExceptionResponse exceptionResponse = GlobalExceptionHandler.handleException(exception);
        req.setAttribute("exception", exceptionResponse);
        servletContext.getRequestDispatcher("/error.jsp").forward(req, resp);
    }
}