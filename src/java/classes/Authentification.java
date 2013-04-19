package classes;

import beans.Project;
import beans.User;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Authentification {

    public static final String DB_URL = "jdbc:mysql://10.0.0.2:3306/ged_bdd";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "1234";
    public static final String SQL_ERROR = "sql_error";
    public static final String USERNAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";
    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public User connectUser(HttpServletRequest request) {
        String username = getFieldValue(request, USERNAME_FIELD);
        String password = getFieldValue(request, PASSWORD_FIELD);
        String username_bdd = null;
        String password_bdd = null;
        Connection connexion = null;
        Statement statement = null;
        ResultSet reqResult = null;

        User user = new User();

        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            SetError(SQL_ERROR, "Driver loading error : " + e.getMessage());
        }

        /* Connexion à la base de données */
        try {
            connexion = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connexion.createStatement();
        } catch (SQLException e) {
            SetError(SQL_ERROR, "Database connection error : " + e.getMessage());
        }

        /* Exécution de la requête */
        try {
            reqResult = statement.executeQuery("SELECT * FROM user WHERE user_username = '" + username + "';");
            if (reqResult.first()) {
                username_bdd = reqResult.getString("user_username");
                password_bdd = reqResult.getString("user_password");
            }
        } catch (SQLException e) {
            SetError(SQL_ERROR, "SQL request error : " + e.getMessage());
        }

        /* Vérification du nom d'utilisateur */
        try {
            if (username == null) {
                throw new Exception("Please specify a username");
            }
            checkUserName(username, username_bdd);
        } catch (Exception e) {
            SetError(USERNAME_FIELD, e.getMessage());
        }
        user.setUsername(username);

        /* Vérification du mot de passe */
        try {
            if (password == null) {
                throw new Exception("Please write your password");
            }
            checkPassword(password, password_bdd);
        } catch (Exception e) {
            SetError(PASSWORD_FIELD, e.getMessage());
        }
        user.setPassword(password);

        if (errors.isEmpty()) {
            result = "Connexion succeed";
        } else {
            result = "Connexion error";
        }

        return user;
    }

    private void checkUserName(String username, String username_bdd) throws Exception {
        if (!username.equals(username_bdd)) {
            throw new Exception("Username unknown");
        }
    }

    private void checkPassword(String password, String password_bdd) throws Exception {
        if (!password.equals(password_bdd)) {
            throw new Exception("Wrong password");
        }
    }

    private void SetError(String field, String message) {
        errors.put(field, message);
    }

    private static String getFieldValue(HttpServletRequest request, String field) {
        String value = request.getParameter(field);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }
}
