package sample.todoapp.Database;
import sample.todoapp.model.User;

import java.sql.*;
import java.util.Arrays;

import static java.sql.DriverManager.getConnection;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    private String gender;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost
                + ":"
                + dbPort + "/"
                + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) throws SQLException, RuntimeException {
        String insert = "INSERT INTO%s(%s,%s,%s,%s,%s,%s)VALUES(?,?,?,?,?,?)".formatted(Const.USERS_TABLE, Const.USERS_FIRSTNAME, Const.USERS_LASTNAME, Const.USERS_USERNAME, Const.USERS_PASSWORD, Const.USERS_LOCATION, Const.USERS_GENDER);
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM " + Const.USERS_TABLE + "WHERE "
                    + Const.USERS_USERNAME + "=?" + "AND" + Const.USERS_PASSWORD
                    + "=?";
            try {
                PreparedStatement preparedStatement;
                try (PreparedStatement ignored = preparedStatement = getDbConnection().prepareStatement(query)) {
                }
                for (String s : Arrays.asList(user.getUserName(), user.getPassword())) {
                    preparedStatement.setString(1, s);
                }
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Please enter your credentials!");

        }

        return resultSet;
    }
}
