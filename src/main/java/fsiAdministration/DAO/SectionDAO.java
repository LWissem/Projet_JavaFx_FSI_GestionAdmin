package fsiAdministration.DAO;

import fsiAdministration.BO.Section;
import fsiAdministration.controllers.ConnexionBDD;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectionDAO extends DAO<Section> {

    @Override
    public boolean create(Section obj) {
        boolean success = false;
        try {
            Connection connect = ConnexionBDD.getConnexion();

            String sql = "INSERT INTO section (libelleSection) VALUES (?);";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, obj.getLibelleSection());

            int rowsInserted = ps.executeUpdate();
            success = rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean delete(Section obj) {
        boolean success = false;
        try {
            Connection connect = ConnexionBDD.getConnexion();

            String sql = "DELETE FROM section WHERE idSection = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, obj.getIdSection());

            int rowsDeleted = ps.executeUpdate();
            success = rowsDeleted > 0;

        } catch (SQLException e) {
<<<<<<< HEAD
            // SQLState 23503 = violation clé étrangère dans PostgreSQL
            if ("23503".equals(e.getSQLState())) {
                throw new RuntimeException("foreign_key_violation");
            } else {
                e.printStackTrace();
            }
=======
            e.printStackTrace();
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
        }
        return success;
    }

<<<<<<< HEAD


=======
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
    @Override
    public boolean update(Section obj) {
        boolean success = false;
        try {
            Connection connect = ConnexionBDD.getConnexion();

            String sql = "UPDATE section SET libelleSection = ? WHERE idSection = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, obj.getLibelleSection());
            ps.setInt(2, obj.getIdSection());

            int rowsUpdated = ps.executeUpdate();
            success = rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Section find(int id) {
        Section section = null;
        try {
            Connection connect = ConnexionBDD.getConnexion();

            String sql = "SELECT * FROM section WHERE idSection = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                section = new Section(
                        rs.getInt("idSection"),
                        rs.getString("libelleSection")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return section;
    }

    @Override
    public List<Section> findAll() {
        List<Section> sections = new ArrayList<>();
        try {
            Connection connect = ConnexionBDD.getConnexion();

            String sql = "SELECT * FROM section";
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Section s = new Section(
                        rs.getInt("idSection"),
                        rs.getString("libelleSection")
                );
                sections.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }
}
