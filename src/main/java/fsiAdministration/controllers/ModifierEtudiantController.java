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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierEtudiantController extends MenuController implements Initializable {

    @FXML private TextField tfNomEtud, tfPrenomEtud;
    @FXML private DatePicker dtNaissance;
    @FXML private ListView<Section> lvSectionEtud;
    @FXML private Button bRetour;

    private Etudiant etudiant; // Étudiant en cours de modification

    // Setter appelé depuis ListeEtudiantController pour passer l'étudiant sélectionné
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        // Pré-remplit les champs avec les valeurs existantes de l'étudiant
        tfNomEtud.setText(etudiant.getNomEtudiant());
        tfPrenomEtud.setText(etudiant.getPrenomEtudiant());
        dtNaissance.setValue(etudiant.getDateNaissance());

        // Sélectionne dans la liste la section actuelle de l'étudiant
        for (Section section : lvSectionEtud.getItems()) {
            if (section.getIdSection() == etudiant.getIdSection()) {
                lvSectionEtud.getSelectionModel().select(section);
                break;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Récupère toutes les sections depuis la base de données et les affiche dans la ListView
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> toutesLesSections = sectionDAO.findAll();
        ObservableList<Section> sectionList = FXCollections.observableArrayList(toutesLesSections);
        lvSectionEtud.setItems(sectionList);
    }

    @FXML
    public void bEnregistrerClick() {
        String nom = tfNomEtud.getText();
        String prenom = tfPrenomEtud.getText();
        LocalDate dateNaissance = dtNaissance.getValue();
        Section sectionSelectionnee = lvSectionEtud.getSelectionModel().getSelectedItem();

        if (etudiant == null || nom.isBlank() || prenom.isBlank() || dateNaissance == null || sectionSelectionnee == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        // Met à jour les champs de l'étudiant
        etudiant.setNomEtudiant(nom);
        etudiant.setPrenomEtudiant(prenom);
        etudiant.setDateNaissance(dateNaissance);
        etudiant.setIdSection(sectionSelectionnee.getIdSection());

        // Enregistre les modifications dans la BDD
        EtudiantDAO dao = new EtudiantDAO();
        boolean success = dao.update(etudiant);

        // Affiche un message selon le résultat
        Alert alert = new Alert(success ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR);
        alert.setTitle(success ? "Réussite" : "Erreur");
        alert.setHeaderText(null);
        alert.setContentText(success ? "Modification réussie." : "Échec de la modification de l'étudiant.");
        alert.showAndWait();

        if (success) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_etudiant.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) bRetour.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Liste des étudiants");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_etudiant.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) bRetour.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Liste des étudiants");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
