package servlets;

import beans.Project;
import classes.GetProjects;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {

    public static final String ATT_USER_SESSION = "userSession";
    public static final String ATT_PROJECTS = "projects";
    public static final String CONNEXION = "/connexion";
    public static final String VIEW = "/WEB-INF/home.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute(ATT_USER_SESSION) == null) {
            this.getServletContext().getRequestDispatcher(CONNEXION).forward(request, response);
        } else {
            GetProjects getPro = new GetProjects();
            List<Project> projects = getPro.getProjects();
            request.setAttribute(ATT_PROJECTS, projects);
            this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
        }
    }
}
