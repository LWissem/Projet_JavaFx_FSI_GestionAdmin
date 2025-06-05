
package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.EtudiantDAO;
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

public class ModifierEtudiantController extends MenuController implements Initializable {

    @FXML private TextField tfNomEtud, tfPrenomEtud;
    @FXML private DatePicker dtNaissance;
    @FXML private ListView<Section> lvSectionEtud;
    @FXML private Button bRetour;
    private Etudiant etudiant;

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        tfNomEtud.setText(etudiant.getNomEtudiant());
        tfPrenomEtud.setText(etudiant.getPrenomEtudiant());
        dtNaissance.setValue(etudiant.getDateNaissance());

        for (Section section : lvSectionEtud.getItems()) {
            if (section.getIdSection() == etudiant.getIdSection()) {
                lvSectionEtud.getSelectionModel().select(section);
                break;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        etudiant.setNomEtudiant(nom);
        etudiant.setPrenomEtudiant(prenom);
        etudiant.setDateNaissance(dateNaissance);
        etudiant.setIdSection(sectionSelectionnee.getIdSection());

        EtudiantDAO dao = new EtudiantDAO();
        boolean success = dao.update(etudiant);

        Alert alert = new Alert(success ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR);
        alert.setTitle(success ? "Reussite" : "Erreur");
        alert.setHeaderText(null);
        alert.setContentText(success ? "Modification reussie." : "Echec de la modification de l'etudiant.");
        alert.showAndWait();

        if (success) {
            Stage stage = (Stage) bRetour.getScene().getWindow();
            stage.close();
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
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
    }
}
