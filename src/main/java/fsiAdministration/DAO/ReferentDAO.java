package fsiAdministration.DAO;

import fsiAdministration.BO.Referent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReferentDAO {
    private Connection connection;

    public ReferentDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean ajouterReferent(Referent referent) {
        String sql = "INSERT INTO REFERENT (nomRef, prenomRef, emailRef) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, referent.getNomRef());
            stmt.setString(2, referent.getPrenomRef());
            stmt.setString(3, referent.getEmaRef());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
