package siit.web;

import siit.services.users.UserService;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("user");
        String password = req.getParameter("password");

        try{
            UserService userService = new UserService();

            //logare cu succes
            req.getSession(true).setAttribute("logged_user", userService.getUserByNameAndPassword(user, password));
            resp.sendRedirect("/customers");

        } catch (AuthenticationException e){
            //failed authentification
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("WEB-INF/login.jsp")
                    .forward(req, resp);

        }

    }
}
