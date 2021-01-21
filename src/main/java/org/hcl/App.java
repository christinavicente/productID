package org.hcl;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )throws ClassNotFoundException {

        List<DrinkDTO> drinks;


        DrinkDAO drinkDAO = new DrinkDAOImpl();
        DrinkDTO drinkDTO1 = drinkDAO.create(
                new DrinkDTO(1L,"water", false)
        );

        DrinkDTO drinkDTO2 = drinkDAO.create(
                new DrinkDTO(2L,"sprite", true)
        );
        DrinkDTO drinkDTO3 = drinkDAO.create(
                new DrinkDTO(3L,"pepsi", true)
        );
        DrinkDTO drinkDTO4 = drinkDAO.create(
                new DrinkDTO(4L,"orange juice", false)
        );
        DrinkDTO drinkDTO5 = drinkDAO.create(
                new DrinkDTO(5L,"milk", false)
        );
        DrinkDTO drinkDTO6 = drinkDAO.create(
                new DrinkDTO(6L,"ginger ale", true)
        );
        drinks = drinkDAO.getAll();

        drinks.forEach((d) -> System.out.println(d.toString()));
        //System.out.println(drinkDAO.isCarbonated(drinkDTO.getId()));
    }

    public static void dbLifeCycleExample() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root")) {

            String ourDatabase = "mynewdatabase";
            try (Statement statement = conn.createStatement()) {

                int affectedRows = statement.executeUpdate("CREATE DATABASE " + ourDatabase);
                if (affectedRows == 0) {
                    System.out.println("no changes");
                } else {
                    System.out.println("database[" + ourDatabase + "] created");
                }

                useDatabase(ourDatabase, statement);

                dropDatabase(ourDatabase, statement);
            }

        } catch (SQLException e) {
            System.out.println("SQL exception");
            e.printStackTrace();
        }
    }
    public static void dropDatabase(String db, Statement statement) {
        try {
            statement.execute("DROP DATABASE " + db);
            System.out.println("database [" + db + "] dropped");
        } catch (SQLException e) {
            System.out.println("Unable to run query");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    public static void useDatabase(String db, Statement statement) {
        try {
            statement.execute("USE " + db);
            System.out.println("switched");
        } catch (SQLException e) {
            System.out.println("Unable to run query");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
    }
}
