package com.mgryziak;

import com.mgryziak.DBqueries.PlayerInsert;
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
            c.setAutoCommit(false);
            PlayerSelect.SelectPlayer_by_ID(c,2);
            PlayerSelect.SelectClub_by_ID(c,12);
            PlayerSelect.SelectData_by_id_pracownika(c,39);
            PlayerSelect.Select_Employee_type_by_id_pracownika(c,156);
            PlayerSelect.Select_MatchTeams_by_MatchNumber(c,5);
            PlayerInsert.AllClubsInsert(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
       // System.out.println("Opened database successfully");

    }
}