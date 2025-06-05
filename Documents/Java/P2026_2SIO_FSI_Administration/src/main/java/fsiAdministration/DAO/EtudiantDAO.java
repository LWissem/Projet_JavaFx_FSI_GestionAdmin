package fsiAdministration.DAO;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Utilisateur;
import fsiAdministration.controllers.ConnexionBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO extends DAO<Etudiant>{


    @Override
    public boolean create(Etudiant obj) {
        boolean controle = false;
        try{
            Connection connect = ConnexionBDD.getConnexion();
            String sql = "Insert into Etudiant(nomEtudiant, prenomEtudiant, idSection,datenaissance) values (?,?,?,?);";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1,obj.getNomEtudiant());
            statement.setString(2,obj.getPrenomEtudiant());
            statement.setInt(3, obj.getIdSection());
            statement.setDate(4, Date.valueOf(obj.getDateNaissance()));
            int rowsInserer = statement.executeUpdate();
            if (rowsInserer > 0) {
                controle= true;
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return controle;
    }

    public int lastId(){
        int controle = 1;

        try {
            Connection connect = ConnexionBDD.getConnexion();

            ResultSet result = connect.createStatement().executeQuery("select max(idEtudiant) from Etudiant ");
            if(result.next()){
                controle = result.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean delete(Etudiant obj) {
        boolean controle = false;
        try {
            Connection connect = ConnexionBDD.getConnexion();
            String sql = "DELETE FROM etudiant WHERE idEtudiant = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, obj.getIdEtudiant());

            int result = ps.executeUpdate();
            controle = (result > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean update(Etudiant obj) {
        boolean success = false;
        try {
            Connection connect = ConnexionBDD.getConnexion();
            String sql = "UPDATE etudiant SET nomEtudiant = ?, prenomEtudiant = ?, dateNaissance = ?, idSection = ? WHERE idEtudiant = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, obj.getNomEtudiant());
            ps.setString(2, obj.getPrenomEtudiant());
            ps.setDate(3, Date.valueOf(obj.getDateNaissance()));
            ps.setInt(4, obj.getIdSection());
            ps.setInt(5, obj.getIdEtudiant());

            int rows = ps.executeUpdate();
            success = (rows > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }


    @Override
    public Etudiant find(int id) {
        return null;
    }

    @Override
    public List findAll() {
        List<Etudiant> mesEtud = new ArrayList<>();
        Etudiant etud;

        try {
            Connection connect = ConnexionBDD.getConnexion();

            String sql = "SELECT * FROM etudiant";
            Statement ps = connect.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()) {
                etud = new Etudiant(
                        rs.getInt("idEtudiant"),
                        rs.getString("nomEtudiant"),
                        rs.getString("prenomEtudiant"),
                        rs.getDate("dateNaissance").toLocalDate()
                );
                etud.setIdSection(rs.getInt("idSection"));
                mesEtud.add(etud);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesEtud;
    }

    public List<Etudiant> findBySection(int idSection) {
        List<Etudiant> list = new ArrayList<>();
        try {
            Connection connect = ConnexionBDD.getConnexion();
            String sql = "SELECT * FROM etudiant WHERE idSection = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, idSection);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Etudiant e = new Etudiant(
                        rs.getInt("idEtudiant"),
                        rs.getString("nomEtudiant"),
                        rs.getString("prenomEtudiant"),
                        rs.getDate("dateNaissance").toLocalDate()
                );
                e.setIdSection(idSection);
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
