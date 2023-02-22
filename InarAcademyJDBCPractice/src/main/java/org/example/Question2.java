package org.example;

import java.sql.*;
//2- Find the staff’s name, username and password who is working in the store that sells “Glass Dying” film
public class Question2 {
    public static void answer() throws SQLException {
        //1- get a connection
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "gzbs0096";
        Connection connection = DriverManager.getConnection(dbURL,username,password);

        //2- create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //3- execute a query
        String query = "SELECT CONCAT(s.first_name,' ',last_name) AS name, username, password \n" +
                "FROM staff s\n" +
                "WHERE store_id IN (\n" +
                "SELECT st.store_id FROM store st\n" +
                "INNER JOIN inventory i ON st.store_id = i.store_id\n" +
                "INNER JOIN film f ON f.film_id = i.film_id\n" +
                "WHERE title = 'Glass Dying');";
        ResultSet resultSet = statement.executeQuery(query);

        //4- process the resultSet
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") +" "+ resultSet.getString("username") +" " +  resultSet.getString("password"));
        }

    }
}
