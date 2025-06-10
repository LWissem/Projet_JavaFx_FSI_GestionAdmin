<<<<<<< HEAD
=======

>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.CoursDAO;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
<<<<<<< HEAD
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
=======
import javafx.fxml.Initializable;
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
<<<<<<< HEAD
=======
import java.time.LocalDate;
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
import java.util.List;
import java.util.ResourceBundle;

public class ModifierCoursController extends MenuController implements Initializable {

    @FXML private TextField tflibelleCours;
    @FXML private TextField tfdescCours;
    @FXML private ListView<Section> lvSectionCours;
    @FXML private Button bRetour;
    private Cours cours;

    public void setCours(Cours cours) {
        this.cours = cours;
        tflibelleCours.setText(cours.getLibelleCours());
        tfdescCours.setText(cours.getDescriptionCours());

        for (Section section : lvSectionCours.getItems()) {
            if (section.getIdSection() == cours.getIdSection()) {
                lvSectionCours.getSelectionModel().select(section);
                break;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> toutesLesSections = sectionDAO.findAll();
        ObservableList<Section> sectionList = FXCollections.observableArrayList(toutesLesSections);
        lvSectionCours.setItems(sectionList);
    }

    @FXML
    public void bEnregistrerClick() {
        String nom = tflibelleCours.getText();
        String prenom = tfdescCours.getText();
        Section sectionSelectionnee = lvSectionCours.getSelectionModel().getSelectedItem();

        if (cours == null || nom.isBlank() || prenom.isBlank() || sectionSelectionnee == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        cours.setLibelleCours(nom);
        cours.setDescriptionCours(prenom);
        cours.setIdSection(sectionSelectionnee.getIdSection());

<<<<<<< HEAD
=======


>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
        CoursDAO dao = new CoursDAO();
        boolean success = dao.update(cours);

        Alert alert = new Alert(success ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR);
<<<<<<< HEAD
        alert.setTitle(success ? "Réussite" : "Erreur");
        alert.setHeaderText(null);
        alert.setContentText(success ? "Modification réussie." : "Échec de la modification du cours.");
        alert.showAndWait();

        if (success) {
            // Au lieu de fermer la fenêtre, on revient à la liste des cours en remplaçant la scène
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_cours.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) bRetour.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Liste des cours");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
=======
        alert.setTitle(success ? "Reussite" : "Erreur");
        alert.setHeaderText(null);
        alert.setContentText(success ? "Modification reussie." : "Echec de la modification du cours.");
        alert.showAndWait();

        if (success) {
            Stage stage = (Stage) bRetour.getScene().getWindow();
            stage.close();
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
        }
    }

    @FXML
    public void bEffacerClick(ActionEvent event) {
        tflibelleCours.clear();
        tfdescCours.clear();
        lvSectionCours.getSelectionModel().clearSelection();
    }
<<<<<<< HEAD

    @FXML
    public void bRetourClick(ActionEvent event) {
        // Même principe, revenir à la liste des cours dans la même fenêtre
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_cours.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) bRetour.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Liste des cours");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
=======
    @FXML
    public void bRetourClick(ActionEvent event) {
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
    }
}
