package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.CoursDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
    }
}

