package fsiAdministration.DAO;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Etudiant;
import fsiAdministration.controllers.ConnexionBDD;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO extends DAO<Cours> {

    @Override
    public boolean create(Cours obj) {
        boolean controle = false;
        try {
            int id = lastId() + 1;
            obj.setIdCours(id);

            Connection connect = ConnexionBDD.getConnexion();

            String sql = "INSERT INTO cours(idCours, libelleCours, descriptionCours, idSection) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setInt(1, obj.getIdCours());
            statement.setString(2, obj.getLibelleCours());
            statement.setString(3, obj.getDescriptionCours());
            statement.setInt(4, obj.getIdSection());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                controle = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    public int lastId() {
        int controle = 0;
        try {
            Connection connect = ConnexionBDD.getConnexion();

            ResultSet result = connect.createStatement().executeQuery("SELECT MAX(idCours) FROM cours");
            if (result.next()) {
                controle = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean delete(Cours obj) {
        boolean controle = false;
        try {
            Connection connect = ConnexionBDD.getConnexion();

            String sql = "DELETE FROM cours WHERE idCours = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, obj.getIdCours());

            int rowsDeleted = ps.executeUpdate();
            controle = (rowsDeleted > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean update(Cours obj) {
        boolean controle = false;
        try {
            Connection connect = ConnexionBDD.getConnexion();

            String sql = "UPDATE cours SET libelleCours = ?, descriptionCours = ?, idSection = ? WHERE idCours = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, obj.getLibelleCours());
            ps.setString(2, obj.getDescriptionCours());
            ps.setInt(3, obj.getIdSection());
            ps.setInt(4, obj.getIdCours());

            int rowsUpdated = ps.executeUpdate();
            controle = (rowsUpdated > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public Cours find(int id) {
        return null;
    }
    public List<Cours> findBySection(int idSection) {
        List<Cours> list = new ArrayList<>();
        try {
            Connection connect = ConnexionBDD.getConnexion();
            String sql = "SELECT * FROM cours WHERE idSection = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, idSection);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cours e = new Cours(
                        rs.getString("libelleCours"),
                        rs.getString("descriptionCours")
                );
                e.setIdSection(idSection);
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Cours> findAll() {
        List<Cours> coursList = new ArrayList<>();
        try {
            Connection connect = ConnexionBDD.getConnexion();

            String sql = "SELECT * FROM cours";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Cours cours = new Cours(
                        rs.getInt("idCours"),
                        rs.getString("libelleCours"),
                        rs.getString("descriptionCours"),
                        rs.getInt("idSection")
                );
                coursList.add(cours);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coursList;
    }
}
