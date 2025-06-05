package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.CoursDAO;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterCoursController extends MenuController implements Initializable {

    @FXML private TextField tflibelleCours, tfdescCours;
    @FXML private ListView<Section> lvSectionCours;
    @FXML private Button bRetour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> toutesLesSections = sectionDAO.findAll();
        ObservableList<Section> sectionList = FXCollections.observableArrayList(toutesLesSections);
        lvSectionCours.setItems(sectionList);
    }

    @FXML
    public void bEnregistrerClick(ActionEvent event) {
        String libelle = tflibelleCours.getText();
        String desc = tfdescCours.getText();
        Section sectionSelectionnee = lvSectionCours.getSelectionModel().getSelectedItem();

        if (libelle.isBlank() || desc.isBlank() || sectionSelectionnee == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }


        Cours newCours = new Cours(libelle, desc);
        newCours.setIdSection(sectionSelectionnee.getIdSection());

        CoursDAO coursDAO = new CoursDAO();
        boolean controle = coursDAO.create(newCours);

        Alert alert = new Alert(controle ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR);
        alert.setTitle(controle ? "Reussite" : "Erreur");
        alert.setHeaderText(null);
        alert.setContentText(controle ? "Ajout reussi." : "Echec de l'ajout du cours.");
        alert.showAndWait();
    }

    @FXML
    public void bEffacerClick(ActionEvent event) {
        tflibelleCours.clear();
        tfdescCours.clear();
        lvSectionCours.getSelectionModel().clearSelection();
    }

    @FXML
    public void bRetourClick(ActionEvent event) {
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
    }
}
