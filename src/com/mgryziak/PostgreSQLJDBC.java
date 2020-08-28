package com.mgryziak;

import com.mgryziak.DBqueries.PlayerSelect;

import java.sql.Connection;
import java.sql.DriverManager;


public class PostgreSQLJDBC {
    public static void main(String args[]) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/kluby",
                            "postgres", "qwerty2462");
            PlayerSelect.SelectPlayer_by_ID(c,2);
            PlayerSelect.SelectData_by_id_pracownika(c,39);
            PlayerSelect.SelectClub_by_ID(c,12);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }
}