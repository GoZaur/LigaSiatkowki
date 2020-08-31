package com.mgryziak.DBqueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class PlayerSelect {
    public static void SelectPlayer_by_ID(Connection c, int id) { //parametr connection z PostgreSQLJDBC a drugi deklarowany przez nas

        Statement stmt = null;
        try {
            /*Class.forName("org.postgresql.Driver");
                c = DriverManager                                                       ta deklaracja jest w connection
                        .getConnection("jdbc:postgresql://localhost:5432/kluby",
                                "postgres", "qwerty2462");*/
            // c.setAutoCommit(false); //zapobieganie automatycznie wygenerowanemu commitowi
            // System.out.println("Opened database successfully"); //wyświetlony komunikat po poprawnej operacji


            stmt = c.createStatement();
            String zapytanie = "SELECT imie, id_klubu FROM pracownicy WHERE id_pracownika = " + id + "";
            //"SELECT p.imie, k.nazwa_klubu FROM pracownicy p, kluby k where p.id_pracownika = "+id+"" +
            //"AND k.id_klubu = "+id_klubu+";";  //String zapytanie - utworzona zmienna =
            // zapytanie bazodanowe i dołączenie zmiennej
            ResultSet rs = stmt.executeQuery(zapytanie);
            //ResultSet rs = stmt.executeQuery( "SELECT * FROM kluby;" );
            while (rs.next()) {
                // int id_klubu = rs.getInt("id_klubu");
                String imie_zawodniczki = rs.getString("imie"); //nazwa kolumny w bazie danch
                //       int id_klubu_zawodniczki = rs.getInt("id_klubu");

                //System.out.println("id_klubu = " + id_klubu);
                System.out.println("Imię zawodniczki = " + imie_zawodniczki);
                //       System.out.println("id klubu = "+id_klubu_zawodniczki);
                System.out.println();
            }
            //rs.close();
            //stmt.close();
            //c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void SelectClub_by_ID(Connection c, int id_klubu) {

        try {
            PreparedStatement rs2 = c.prepareStatement("SELECT nazwa_klubu FROM kluby WHERE id_klubu =?");
            rs2.setInt(1, id_klubu);

            ResultSet resultSet = rs2.executeQuery();  //(sql)
            while (resultSet.next()) {
                System.out.println("Nazwa klubu = " + resultSet.getString("nazwa_klubu"));
                System.out.println();
                //rs2.close();
                //resultSet.close();
                //c.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void SelectData_by_id_pracownika(Connection c, int id_pracownika) {

        try {
            PreparedStatement stmt3 = c.prepareStatement("select " +
                    "sum(s.liczba_asow_serwisowych) as \"Suma wykonanych asów serwisowych\", m.kwota_wynagrodzenia, k.nazwa_klubu \n" +
                    "from statystyki s, pensje m, kluby k where s.id_pracownika = ? and s.id_pracownika = m.id_pracownika \n" +
                    "and k.id_klubu = m.id_klubu\n" +
                    "group by m.kwota_wynagrodzenia, k.nazwa_klubu");

            stmt3.setInt(1, id_pracownika);

            ResultSet resultSet = stmt3.executeQuery();
            while (resultSet.next()) {
                System.out.println("Nazwa klubu = " + resultSet.getString("nazwa_klubu"));
                System.out.println("Kwota wynagrodzenia = " + resultSet.getInt("kwota_wynagrodzenia") + "zł");
                System.out.println("Liczba zdobytych asów serwisowych = " + resultSet.getInt("Suma wykonanych asów serwisowych"));
            }
            //stmt3.close();
            //resultSet.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void Select_Employee_type_by_id_pracownika(Connection c, int id_pracownika) {
        try {
            PreparedStatement stmt4 = c.prepareStatement("Select t.stanowisko as \"Stanowisko pracownika\"" +
                    "From stanowiska_pracownikow t, pracownicy p\n Where p.id_pracownika = ? and t.id = p.stanowisko");

            stmt4.setInt(1, id_pracownika);

            ResultSet resultSet = stmt4.executeQuery();
            while (resultSet.next()) {
                System.out.println("Stanowisko pracownika = " + resultSet.getString("Stanowisko pracownika"));
            }
            //stmt4.close();
            //resultSet.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void Select_MatchTeams_by_MatchNumber(Connection c, int nr_meczu) {
        try {
            PreparedStatement stmt5 = c.prepareStatement("Select k1.nazwa_klubu as gospodarz, k2.nazwa_klubu as gosc\n" +
                    "                    From mecze, kluby as k1, kluby as k2\n" +
                    "                    Where mecze.nr_meczu = ? and k2.id_klubu = mecze.gosc and k1.id_klubu = mecze.gospodarz");

            stmt5.setInt(1, nr_meczu);

            ResultSet resultSet = stmt5.executeQuery();
            while (resultSet.next()) {
                System.out.println("Nazwy klubów rozgrywajacych spotkanie:\ngospodarz - "
                        + resultSet.getString("gospodarz" ) + "\ngosc - "+ resultSet.getString("gosc"));
            }
            stmt5.close();
            resultSet.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

}