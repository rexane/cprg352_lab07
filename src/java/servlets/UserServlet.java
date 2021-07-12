package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService us = new UserService();
        RoleService rs = new RoleService();

        try {
            request.setAttribute("users", us.getAll());
            request.setAttribute("roles", rs.getAll());
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService us = new UserService();
        RoleService rs = new RoleService();

        String email = request.getParameter("email");
        int activity = Integer.parseInt(request.getParameter("activity"));
        boolean active = false;
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        Role roleId = request.getParameter("roleId");

        String action = request.getParameter("action");

        if (action.equals("add")) {
            if (email == null || email.equals("")) {
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
            }
            if (activity == 0) {
                active = true;
            } else if (activity == 1) {
                active = false;
            }

            try {
                us.insert(email, active, firstname, lastname, password, roleId);
                response.sendRedirect("users");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (action.equals("delete")) {
            try {
                us.delete(email);
                response.sendRedirect("users");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
