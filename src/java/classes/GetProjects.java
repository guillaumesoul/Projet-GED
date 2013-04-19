package classes;

import beans.Project;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetProjects {

    public static final String DB_URL = "jdbc:mysql://10.0.0.2:3306/ged_bdd";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "1234";
    public List<String> errors = new ArrayList<String>();

    public List<String> getErrors() {
        return errors;
    }

    private void SetError(String message) {
        errors.add(message);
    }

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<Project>();
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            SetError("Driver loading error");
        }
        /* Connexion à la base de données */
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connexion = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT project_title FROM project;");
            while (result.next()) {
                String title = result.getString("project_title");
                Project pro = new Project();
                pro.setTitle(title);
                projects.add(pro);

                /* Ici, nous placerons nos requêtes vers la BDD */
                /* ... */
            }
        } catch (SQLException e) {
            SetError("Database connection error : " + e.getMessage());
        } finally {
            if (connexion != null) {
                try {
                    /* Fermeture de la connexion */
                    connexion.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return projects;
    }
}