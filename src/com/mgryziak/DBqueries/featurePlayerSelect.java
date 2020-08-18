package com.mgryziak.DBqueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


    public class featurePlayerSelect {
        public static void main( String args[] ) {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/kluby",
                                "postgres", "qwerty2462");
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM kluby;" );
                while ( rs.next() ) {
                    int id_klubu = rs.getInt("id_klubu");
                    String  nazwa_klubu = rs.getString("nazwa_klubu");

                    System.out.println( "id_klubu = " + id_klubu );
                    System.out.println( "nazwa_klubu = " + nazwa_klubu );
                    System.out.println();
                }
                rs.close();
                stmt.close();
                c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                System.exit(0);
            }
            System.out.println("Operation done successfully");
        }
    }
