package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.EtudiantDAO;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterEtudiantController extends MenuController implements Initializable {
    // Champs de saisie pour le nom, prénom et date de naissance de l'étudiant
    @FXML private TextField tfNomEtud, tfPrenomEtud;
    @FXML private DatePicker dtNaissance;
    // Liste déroulante pour sélectionner la section
    @FXML private ListView<Section> lvSectionEtud;
    @FXML private Button bRetour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Récupérer toutes les sections depuis la base de données
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> toutesLesSections = sectionDAO.findAll();
        // Convertir en liste observable pour affichage dans la ListView
        ObservableList<Section> sectionList = FXCollections.observableArrayList(toutesLesSections);
        lvSectionEtud.setItems(sectionList);
    }

    @FXML
    public void bEnregistrerClick(ActionEvent event) {
        String nom = tfNomEtud.getText();
        String prenom = tfPrenomEtud.getText();
        LocalDate dateNaissance = dtNaissance.getValue();
        Section sectionSelectionnee = lvSectionEtud.getSelectionModel().getSelectedItem();

        // Vérification que tous les champs sont remplis
        if (nom.isBlank() || prenom.isBlank() || dateNaissance == null || sectionSelectionnee == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        // Création de l'objet Etudiant
        Etudiant newEtud = new Etudiant(nom, prenom, dateNaissance);
        newEtud.setIdSection(sectionSelectionnee.getIdSection());

        // Insertion dans la base de données
        EtudiantDAO etudDAO = new EtudiantDAO();
        boolean controle = etudDAO.create(newEtud);

        // Affichage d'une alerte de confirmation ou d'erreur
        Alert alert = new Alert(controle ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR);
        alert.setTitle(controle ? "Reussite" : "Erreur");
        alert.setHeaderText(null);
        alert.setContentText(controle ? "Ajout reussi." : "Echec de l'ajout de l'etudiant.");
        alert.showAndWait();
    }

    @FXML
    public void bEffacerClick(ActionEvent event) {
        tfNomEtud.clear();
        tfPrenomEtud.clear();
        dtNaissance.setValue(null);
        lvSectionEtud.getSelectionModel().clearSelection();
    }

    @FXML
    public void bRetourClick(ActionEvent event) {
        try {
            // Charger la page d'accueil (remplace "Accueil.fxml" par le nom correct)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_etudiant.fxml"));
            Parent root = loader.load();

            // Obtenir la scène actuelle à partir du bouton
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène (page d'accueil)
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
