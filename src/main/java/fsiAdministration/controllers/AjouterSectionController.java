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

public class AjouterSectionController extends MenuController implements Initializable {

    @FXML private TextField tflibelleSection;
    @FXML private Button bRetour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void bEnregistrerClick(ActionEvent event) {
        String libelle = tflibelleSection.getText();

        if (libelle.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }


        Section newSection = new Section(0, libelle);
        SectionDAO sectionDAO = new SectionDAO();
        boolean controle = sectionDAO.create(newSection);


        Alert alert = new Alert(controle ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR);
        alert.setTitle(controle ? "Reussite" : "Erreur");
        alert.setHeaderText(null);
        alert.setContentText(controle ? "Ajout reussi." : "Echec de l'ajout de la section.");
        alert.showAndWait();
    }

    @FXML
    public void bEffacerClick(ActionEvent event) {
        tflibelleSection.clear();
    }

    @FXML
    public void bRetourClick(ActionEvent event) {
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
    }
}
