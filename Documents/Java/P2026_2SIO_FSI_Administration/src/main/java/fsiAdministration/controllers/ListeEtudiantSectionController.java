package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.CoursDAO;
import fsiAdministration.DAO.EtudiantDAO;
import fsiAdministration.DAO.SectionDAO;
import javafx.beans.property.SimpleStringProperty;
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

public class ListeEtudiantSectionController extends MenuController implements Initializable {
    @FXML
    private Button bRetour;
    @FXML
    private Label lbTitre;
    @FXML
    private TableView<Etudiant> tvEtudiants;
    @FXML
    private TableColumn<Etudiant, String> tcNom;
    @FXML
    private TableColumn<Etudiant, String> tcPrenom;
    @FXML
    private TableColumn<Etudiant, String> tcDateNaissance;

    public void setSection(Section section) {
        EtudiantDAO dao = new EtudiantDAO();
        List<Etudiant> etudiants = dao.findBySection(section.getIdSection());
        tvEtudiants.setItems(FXCollections.observableArrayList(etudiants));
        lbTitre.setText("Etudiants de la section " + section.getLibelleSection());


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcNom.setCellValueFactory(cellData -> cellData.getValue().nomEtudiantProperty());
        tcPrenom.setCellValueFactory(cellData -> cellData.getValue().prenomEtudiantProperty());
        tcDateNaissance.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateNaissance().toString()));

    }
    @FXML
    public void bRetourClick(ActionEvent event) {
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
    }
}

