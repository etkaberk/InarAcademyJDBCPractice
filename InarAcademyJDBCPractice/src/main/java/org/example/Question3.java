package org.example;

import java.sql.*;

//3-What are the names of top 5 categories, that have more than 150 films in inventory of store-1?
public class Question3 {
    public static void answer() throws SQLException {
        //1- get a connection
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String username = "postgres";
        String password = "gzbs0096";
        Connection connection = DriverManager.getConnection(dbURL,username,password);

        //2- create a stetament
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //3- execute a query
        String query = "SELECT c.name,COUNT(*) AS film_amount\n" +
                "FROM category c\n" +
                "INNER JOIN film_category fc ON c.category_id = fc.category_id\n" +
                "INNER JOIN inventory i ON i.film_id = fc.film_id\n" +
                "GROUP BY c.name,store_id\n" +
                "HAVING COUNT(*) > 150 AND store_id = 1\n" +
                "ORDER BY film_amount DESC\n" +
                "LIMIT 5";
        ResultSet resultSet = statement.executeQuery(query);

        //4- process the resultSet
            while (resultSet.next()){
                System.out.println(resultSet.getString("name")+" " +  resultSet.getString("film_amount"));
            }


    }
}
