package fsiAdministration.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
<<<<<<< HEAD
    //private static final String URL = "jdbc:postgresql://172.20.102.201:5432/P2025_FSI_G3";
    //private static final String USER = "groupe1";
    //private static final String PASSWORD = "2SIO_ORT";

    private static final String URL = "jdbc:postgresql://localhost:5432/FSI_GestionAdmin";
    private static final String USER = "postgres";
    private static final String PASSWORD = "UFO_Thespoot1";
=======
        private static final String URL = "jdbc:postgresql://localhost:5432/FSI_GestionAdmin";
        private static final String USER = "postgres";
        private static final String PASSWORD = "UFO_Thespoot1";
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}