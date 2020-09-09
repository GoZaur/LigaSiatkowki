package com.mgryziak.DBqueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class PlayerSelect {
    public static void SelectPlayer_by_ID(Connection c, int id) { //parametr connection z PostgreSQLJDBC a drugi deklarowany przez nas

        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String zapytanie = "SELECT imie, id_klubu FROM pracownicy WHERE id_pracownika = " + id + "";
            ResultSet rs = stmt.executeQuery(zapytanie);

            while (rs.next()) {
                String imie_zawodniczki = rs.getString("imie"); //nazwa kolumny w bazie danch
                System.out.println("Imię zawodniczki = " + imie_zawodniczki);
                System.out.println();
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void SelectClub_by_ID(Connection c, int id_klubu) {

        try {
            PreparedStatement rs2 = c.prepareStatement("SELECT nazwa_klubu FROM kluby WHERE id_klubu =?");
            rs2.setInt(1, id_klubu);

            ResultSet resultSet = rs2.executeQuery();
            while (resultSet.next()) {
                System.out.println("Nazwa klubu = " + resultSet.getString("nazwa_klubu"));
                System.out.println();
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

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void Select_MatchTeams_by_MatchNumber(Connection c, int nr_meczu) {
        try {
            PreparedStatement stmt5 = c.prepareStatement("Select k1.nazwa_klubu as gospodarz, k2.nazwa_klubu as gosc\n" +
                    "From mecze, kluby as k1, kluby as k2\n" +
                    "Where mecze.nr_meczu = ? and k2.id_klubu = mecze.gosc and k1.id_klubu = mecze.gospodarz");

            stmt5.setInt(1, nr_meczu);

            ResultSet resultSet = stmt5.executeQuery();
            while (resultSet.next()) {
                System.out.println("Nazwy klubów rozgrywajacych spotkanie:\ngospodarz - "
                        + resultSet.getString("gospodarz" ) + "\ngosc - "+ resultSet.getString("gosc"));
            }
           // stmt5.close();
            //resultSet.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    public static void ResultOfAllSelect(Connection c){
        SelectPlayer_by_ID(c,2);
        SelectClub_by_ID(c,12);
        SelectData_by_id_pracownika(c,39);
        Select_Employee_type_by_id_pracownika(c,156);
        Select_MatchTeams_by_MatchNumber(c,5);
    }
}