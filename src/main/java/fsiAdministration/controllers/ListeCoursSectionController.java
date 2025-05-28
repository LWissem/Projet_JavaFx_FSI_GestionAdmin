package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.CoursDAO;
import javafx.collections.FXCollections;
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
import java.util.List;
import java.util.ResourceBundle;

public class ListeCoursSectionController extends MenuController implements Initializable {

    @FXML
    private Button bRetour;
    @FXML
    private Label lbTitre;
    @FXML
    private TableView<Cours> tvCours;
    @FXML
    private TableColumn<Cours, String> tcLibelleCours;
    @FXML
    private TableColumn<Cours, String> tcDescriptionCours;

    public void setSection(Section section) {
        CoursDAO dao = new CoursDAO();
        List<Cours> cours = dao.findBySection(section.getIdSection());
        tvCours.setItems(FXCollections.observableArrayList(cours));
        lbTitre.setText("Cours de la section " + section.getLibelleSection());


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tcLibelleCours.setCellValueFactory(cellData -> cellData.getValue().libelleCoursProperty());
        tcDescriptionCours.setCellValueFactory(cellData -> cellData.getValue().descriptionCoursProperty());

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

