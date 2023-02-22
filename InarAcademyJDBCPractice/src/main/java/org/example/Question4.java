package org.example;

import java.sql.*;

//4-Which countries have made higher payments than 800$?(with their payment amount in desc order)
public class Question4 {
    public static void answer() throws SQLException {
        //1-get a connection
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "gzbs0096";
        Connection connection = DriverManager.getConnection(dbURL,username,password);

        //2-create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //3-execute query
        String query = "SELECT country,SUM(amount) FROM country c\n" +
                "INNER JOIN city ci ON ci.country_id = c.country_id\n" +
                "INNER JOIN address ad ON ad.city_id = ci.city_id\n" +
                "INNER JOIN customer cu ON cu.address_id = ad.address_id\n" +
                "INNER JOIN payment p ON p.customer_id = cu.customer_id\n" +
                "GROUP BY country\n" +
                "HAVING SUM(amount) > 800\n" +
                "ORDER BY SUM(amount) DESC;";
        ResultSet resultSet = statement.executeQuery(query);

        //4-process resultSet
        while (resultSet.next()) {
            System.out.println(resultSet.getString("country") + "  " + resultSet.getString("sum"));
        }
    }
}
