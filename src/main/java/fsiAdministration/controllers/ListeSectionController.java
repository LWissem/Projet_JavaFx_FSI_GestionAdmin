package fsiAdministration.controllers;

import fsiAdministration.BO.Section;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeSectionController extends MenuController implements Initializable {
    @FXML
    private Button bRetour;

    @FXML
    private TableView<Section> tvSections;

    @FXML
    private TableColumn<Section, Void> tcActions;

    @FXML
    private TableColumn<Section, String> tcLibelleSection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SectionDAO sectionDAO = new SectionDAO();
        List<Section> listeSection = sectionDAO.findAll();
        ObservableList<Section> mesSections = FXCollections.observableArrayList(listeSection);

        tcLibelleSection.setCellValueFactory(cellData -> cellData.getValue().libelleSectionProperty());
        tvSections.setItems(mesSections);

        Callback<TableColumn<Section, Void>, TableCell<Section, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btnModifier = new Button("Modifier");
            private final Button btnSupprimer = new Button("Supprimer");
            private final Button btnEtudiants = new Button ("Voir les etudiants");
            private final  Button btnCours = new Button("Voir les cours");

            {
                btnModifier.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                btnSupprimer.setStyle("-fx-background-color: red; -fx-text-fill: white;");
                btnEtudiants.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
                btnCours.setStyle("-fx-background-color: purple; -fx-text-fill: white;");

                btnModifier.setOnAction(event -> {
                    try {

                        // Charger le fichier FXML
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modifier_section.fxml"));
                        Parent root = fxmlLoader.load();


                        // Obtenir le contrôleur de la nouvelle fenetre
                        ModifierSectionController modifierSectionController = fxmlLoader.getController();
                        Section section = getTableView().getItems().get(getIndex());
                        modifierSectionController.setSection(section);

                        // Créer une nouvelle fenêtre (Stage)
                        Stage stage = new Stage();
                        stage.setTitle("Modifier une section");
                        stage.setScene(new Scene(root));

                        // Configurer la fenêtre en tant que modal
                        stage.initModality(Modality.APPLICATION_MODAL);

                        // Afficher la fenêtre et attendre qu'elle se ferme
                        stage.showAndWait();
                        tvSections.setItems(FXCollections.observableArrayList(new SectionDAO().findAll()));



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                btnSupprimer.setOnAction(event -> {
                    Section section = getTableView().getItems().get(getIndex());
                    Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmAlert.setTitle("Confirmation");
                    confirmAlert.setHeaderText(null);
                    confirmAlert.setContentText("Voulez-vous vraiment supprimer la section " + section.getLibelleSection() + " ?");

                    confirmAlert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            SectionDAO dao = new SectionDAO();
                            boolean deleted = dao.delete(section);
                            if (deleted) {
                                getTableView().getItems().remove(section);
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Succes");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Section supprime.");
                                successAlert.showAndWait();
                            } else {
                                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                errorAlert.setTitle("Erreur");
                                errorAlert.setHeaderText(null);
                                errorAlert.setContentText("Echec de la suppression de la section.");
                                errorAlert.showAndWait();
                            }
                        }
                    });
                });

                btnEtudiants.setOnAction(event -> {
                    try {

                        // Charger le fichier FXML
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_section_etudiants.fxml"));
                        Parent root = fxmlLoader.load();


                        // Obtenir le contrôleur de la nouvelle fenetre
                        ListeEtudiantSectionController listeEtudiantSectionController = fxmlLoader.getController();
                        Section section = getTableView().getItems().get(getIndex());
                        listeEtudiantSectionController.setSection(section);

                        // Créer une nouvelle fenêtre (Stage)
                        Stage stage = new Stage();
                        stage.setTitle("Liste des etudiants");
                        stage.setScene(new Scene(root));

                        // Configurer la fenêtre en tant que modal
                        stage.initModality(Modality.APPLICATION_MODAL);

                        // Afficher la fenêtre et attendre qu'elle se ferme
                        stage.showAndWait();
                        tvSections.setItems(FXCollections.observableArrayList(new SectionDAO().findAll()));



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                btnCours.setOnAction(event -> {
                    try {

                        // Charger le fichier FXML
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_section_cours.fxml"));
                        Parent root = fxmlLoader.load();


                        // Obtenir le contrôleur de la nouvelle fenetre
                        ListeCoursSectionController listeCoursSectionController = fxmlLoader.getController();
                        Section section = getTableView().getItems().get(getIndex());
                        listeCoursSectionController.setSection(section);

                        // Créer une nouvelle fenêtre (Stage)
                        Stage stage = new Stage();
                        stage.setTitle("Liste des cours");
                        stage.setScene(new Scene(root));

                        // Configurer la fenêtre en tant que modal
                        stage.initModality(Modality.APPLICATION_MODAL);

                        // Afficher la fenêtre et attendre qu'elle se ferme
                        stage.showAndWait();
                        tvSections.setItems(FXCollections.observableArrayList(new SectionDAO().findAll()));



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(10, btnModifier, btnSupprimer, btnEtudiants, btnCours));
                }
            }
        };

        tcActions.setCellFactory(cellFactory);
        tvSections.setItems(mesSections);


    }
    @FXML
    public void bRetourClick(ActionEvent event) {
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
    }
}


