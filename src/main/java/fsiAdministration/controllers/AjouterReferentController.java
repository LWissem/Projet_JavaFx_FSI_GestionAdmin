package fsiAdministration.controllers;

import fsiAdministration.BO.Referent;
import fsiAdministration.DAO.ReferentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class AjouterReferentController {

    @FXML
    private TextField tfNomRef;

    @FXML
    private TextField tfPrenomRef;

    @FXML
    private TextField tfEmailRef;

    @FXML
    public void bEnregistrerClick(ActionEvent event) {
        String nom = tfNomRef.getText().trim();
        String prenom = tfPrenomRef.getText().trim();
        String email = tfEmailRef.getText().trim();

        if (nom.isBlank() || prenom.isBlank() || email.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        Referent nouveauReferent = new Referent(0, nom, prenom, email);

        try (Connection conn = ConnexionBDD.getConnexion()) {
            ReferentDAO referentDAO = new ReferentDAO(conn);
            boolean controle = referentDAO.ajouterReferent(nouveauReferent);

            Alert alert = new Alert(controle ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR);
            alert.setTitle(controle ? "Réussite" : "Erreur");
            alert.setHeaderText(null);
            alert.setContentText(controle ? "Ajout réussi." : "Échec de l'ajout du référent.");
            alert.showAndWait();

            if (controle) {
                bEffacerClick(); // effacer les champs après succès
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur base de données");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la connexion à la base.");
            alert.showAndWait();
        }
    }

    @FXML
    public void bEffacerClick() {
        tfNomRef.clear();
        tfPrenomRef.clear();
        tfEmailRef.clear();
    }

    public void bRetourClick(ActionEvent actionEvent) {
    }
}
