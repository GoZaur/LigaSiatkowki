package com.mgryziak;

import com.mgryziak.DBqueries.PlayerInsert;
import com.mgryziak.DBqueries.PlayerSelect;
import com.mgryziak.DBqueries.PlayerUpdate;

import java.sql.Connection;
import java.sql.Date;
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
            PlayerSelect.ResultOfAllSelect(c);
            PlayerInsert.AllClubsInsert(c);
            PlayerInsert.AllSupport(c);
            PlayerInsert.InsertClubsSupport(c, 5, 11, Date.valueOf("2019-10-23"), 45350);
            PlayerUpdate.updateExpenses(c, 78100.00, 3);
            PlayerUpdate.updateSurname(c, "Zielińska", 10);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}