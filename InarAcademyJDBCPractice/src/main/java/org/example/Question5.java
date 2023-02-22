package org.example;

import java.sql.*;

//5-How much will store-2 earn when all the rented films are returned?
public class Question5 {
    public static void answer() throws SQLException {
        //1- get a connection
        String dbURL = "jdbc:postgresql://localhost:5432/dvdrental";
        String userName ="postgres";
        String password ="gzbs0096";
        Connection connection = DriverManager.getConnection(dbURL,userName,password);

        //2- create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //3- execute a query
        String query = "SELECT SUM(amount) AS total\n" +
                "FROM payment p \n" +
                "JOIN rental r ON r.rental_id= p.rental_id\n" +
                "JOIN inventory i ON i.inventory_id =r.inventory_id\n" +
                "WHERE store_id =2 AND r.return_date IS NULL";
        ResultSet resultSet = statement.executeQuery(query);

        //4- process the resultSet
        while (resultSet.next()){
            System.out.println(resultSet.getString("total"));
        }





    }

}
