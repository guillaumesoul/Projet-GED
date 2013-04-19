package servlets;

import beans.User;
import classes.Authentification;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Connexion extends HttpServlet {

    public static final String ATT_AUTH = "auth";
    public static final String ATT_USER_SESSION = "userSession";
    public static final String VIEW = "/WEB-INF/connexion.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Authentification auth = new Authentification();
        User userSession = auth.connectUser(request);
        HttpSession session = request.getSession();

        request.setAttribute(ATT_AUTH, auth);

        if (auth.getErrors().isEmpty()) {
            session.setAttribute(ATT_USER_SESSION, userSession);
            /*this.getServletContext().getRequestDispatcher("/home").forward(request, response);*/
            response.sendRedirect("home");
        } else {
            session.setAttribute(ATT_USER_SESSION, null);
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        }
    }
}
