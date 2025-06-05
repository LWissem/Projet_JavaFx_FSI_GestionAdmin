
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

public class ModifierSectionController extends MenuController implements Initializable {

    @FXML private TextField tflibelleSection;
    @FXML private Button bRetour;
    private Section section;

    public void setSection(Section section) {
        this.section = section;
        tflibelleSection.setText(section.getLibelleSection());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void bEnregistrerClick() {
        String libelle = tflibelleSection.getText();

        if (section == null || libelle.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }
        section.setLibelleSection(libelle);

        SectionDAO dao = new SectionDAO();
        boolean success = dao.update(section);

        Alert alert = new Alert(success ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR);
        alert.setTitle(success ? "Reussite" : "Erreur");
        alert.setHeaderText(null);
        alert.setContentText(success ? "Modification reussie." : "Echec de la modification de la section.");
        alert.showAndWait();

        if (success) {
            Stage stage = (Stage) bRetour.getScene().getWindow();
            stage.close();
        }
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
