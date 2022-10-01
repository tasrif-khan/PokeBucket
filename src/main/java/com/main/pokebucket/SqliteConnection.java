package com.main.pokebucket;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {



    public static Connection getDBConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:pokemon.db");

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }


    }

}
