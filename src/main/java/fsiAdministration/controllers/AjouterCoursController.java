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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
=======
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
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
<<<<<<< HEAD
        try {
            // Charger la page d'accueil (remplace "Accueil.fxml" par le nom correct)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_cours.fxml"));
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
=======
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
    }
}
