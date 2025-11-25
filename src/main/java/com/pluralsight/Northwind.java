package com.pluralsight;

import java.sql.*;

public class Northwind {
    public static void main(String[] args) {

        try {
            if(args.length !=2){
                System.out.println( "This application need to correct username and password");

            }
            String username = args[0];
            String password = args[1];

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password
            );

            String query = """
                    SELECT ProductID, ProductName, UnitPrice, UnitsInStock
                    FROM products;
                    
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()){

                int ProductID = results.getInt( "ProductID");
                String ProductName = results.getString( "ProductName");
                double UnitPrice = results.getDouble("UnitPrice");
                int UnitsInStock = results.getInt("UnitsInStock" );

                System.out.println("ProductID: " + ProductID);
                System.out.println("ProductName: " + ProductName);
                System.out.println("UnitsInStock: " + UnitsInStock);


            }
            results.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Run time error");
           e.printStackTrace();

        }

    }

}
