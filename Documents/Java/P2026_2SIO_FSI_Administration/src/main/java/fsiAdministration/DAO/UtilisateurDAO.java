package fsiAdministration.DAO;

import fsiAdministration.BO.Utilisateur;
import fsiAdministration.controllers.Session;
import fsiAdministration.controllers.ConnexionBDD;

import java.sql.*;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur>{


    @Override
    public boolean create(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean update(Utilisateur obj) {
        return false;
    }

    @Override
    public Utilisateur find(int id) {
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        return List.of();
    }

    public Utilisateur find(String login, String password) {
        Utilisateur user = null;

        try {
            Connection connect = ConnexionBDD.getConnexion();


            String sql = "SELECT * FROM utilisateur WHERE loginUtilisateur = ? AND mdpUtilisateur = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                user = new Utilisateur(
                        result.getInt("idutilisateur"),
                        result.getString("loginutilisateur"),
                        ""
                );

                Session.utilisateurConnecte = user;
            }

        } catch (SQLException e) {
            return null;
        }
        return user;
    }
}
