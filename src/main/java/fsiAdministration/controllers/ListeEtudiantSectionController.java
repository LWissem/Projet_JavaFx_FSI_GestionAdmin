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
        try {
            // Charger la page d'accueil (remplace "Accueil.fxml" par le nom correct)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_section.fxml"));
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

