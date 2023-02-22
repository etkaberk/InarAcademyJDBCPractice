package org.example;

import java.sql.*;
//1- The last 3 person’s name and rent date, who had rent any film of actor “Ed Chase”
public class Question1 {
public static void answer() throws SQLException {
    //1- get a connection
    String dbURL ="jdbc:postgresql://localhost:5432/dvdrental";
    String userName ="postgres";
    String password = "gzbs0096";
    Connection connection = DriverManager.getConnection(dbURL,userName,password);

    //2-create a statement object
    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

    //- 3- execute a query
    String query = " \n" +
            "SELECT customer.first_name, customer.last_name, rental.rental_date FROM rental\n" +
            "JOIN inventory ON rental.inventory_id = inventory.inventory_id\n" +
            "JOIN film_actor ON inventory.film_id = film_actor.film_id\n" +
            "JOIN actor ON film_actor.actor_id = actor.actor_id\n" +
            "JOIN customer ON rental.customer_id = customer.customer_id \n" +
            "WHERE actor.first_name = 'Ed' AND actor.last_name = 'Chase'\n" +
            "ORDER BY rental.rental_date DESC \n" +
            "LIMIT 3;";
    ResultSet resultSet = statement.executeQuery(query);

    //4- process the resultSet
    while (resultSet.next()){
        System.out.println(resultSet.getString("first_name")+" "+ resultSet.getString("last_name")+" " + resultSet.getString("rental_date"));
    }
}
}

