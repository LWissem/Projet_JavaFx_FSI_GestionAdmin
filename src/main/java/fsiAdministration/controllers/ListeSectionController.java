package fsiAdministration.controllers;

import fsiAdministration.BO.Section;
import fsiAdministration.DAO.SectionDAO;
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
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
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
                        // Fermer la fenêtre actuelle
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentStage.close();

                        // Charger le fichier FXML
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modifier_section.fxml"));
                        Parent root = fxmlLoader.load();

                        // Obtenir le contrôleur de la nouvelle fenêtre
                        ModifierSectionController modifierSectionController = fxmlLoader.getController();
                        Section section = getTableView().getItems().get(getIndex());
                        modifierSectionController.setSection(section);

                        // Créer une nouvelle fenêtre (Stage)
                        Stage stage = new Stage();
                        stage.setTitle("Modifier une section");
                        stage.setScene(new Scene(root));

                        // Configurer la fenêtre en tant que modal
                        stage.initModality(Modality.APPLICATION_MODAL);

                        // Afficher la nouvelle fenêtre
                        stage.show();

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
                            try {
                                boolean deleted = dao.delete(section);
                                if (deleted) {
                                    getTableView().getItems().remove(section);
                                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                    successAlert.setTitle("Succès");
                                    successAlert.setHeaderText(null);
                                    successAlert.setContentText("Section supprimée.");
                                    successAlert.showAndWait();
                                } else {
                                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                    errorAlert.setTitle("Erreur");
                                    errorAlert.setHeaderText(null);
                                    errorAlert.setContentText("Échec de la suppression de la section.");
                                    errorAlert.showAndWait();
                                }
                            } catch (RuntimeException ex) {
                                if ("foreign_key_violation".equals(ex.getMessage())) {
                                    Alert fkAlert = new Alert(Alert.AlertType.ERROR);
                                    fkAlert.setTitle("Erreur");
                                    fkAlert.setHeaderText(null);
                                    fkAlert.setContentText("Erreur : Vous ne pouvez pas supprimer cette section car elle est liée à un ou plusieurs cours/étudiants.");
                                    fkAlert.showAndWait();
                                } else {
                                    ex.printStackTrace();
                                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                    errorAlert.setTitle("Erreur inattendue");
                                    errorAlert.setHeaderText(null);
                                    errorAlert.setContentText("Une erreur est survenue.");
                                    errorAlert.showAndWait();
                                }
                            }
                        }
                    });
                });


                btnEtudiants.setOnAction(event -> {
                    try {
                        // Fermer la fenêtre actuelle
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentStage.close();

                        // Charger le fichier FXML
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_section_etudiants.fxml"));
                        Parent root = fxmlLoader.load();

                        // Obtenir le contrôleur de la nouvelle fenêtre
                        ListeEtudiantSectionController listeEtudiantSectionController = fxmlLoader.getController();
                        Section section = getTableView().getItems().get(getIndex());
                        listeEtudiantSectionController.setSection(section);

                        // Créer une nouvelle fenêtre (Stage)
                        Stage stage = new Stage();
                        stage.setTitle("Liste des étudiants");
                        stage.setScene(new Scene(root));

                        // Configurer la fenêtre en tant que modal
                        stage.initModality(Modality.APPLICATION_MODAL);

                        // Afficher la nouvelle fenêtre
                        stage.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                btnCours.setOnAction(event -> {
                    try {
                        // Fermer la fenêtre actuelle
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentStage.close();

                        // Charger le fichier FXML
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_section_cours.fxml"));
                        Parent root = fxmlLoader.load();

                        // Obtenir le contrôleur de la nouvelle fenêtre
                        ListeCoursSectionController listeCoursSectionController = fxmlLoader.getController();
                        Section section = getTableView().getItems().get(getIndex());
                        listeCoursSectionController.setSection(section);

                        // Créer une nouvelle fenêtre (Stage)
                        Stage stage = new Stage();
                        stage.setTitle("Liste des cours");
                        stage.setScene(new Scene(root));

                        // Configurer la fenêtre en tant que modal
                        stage.initModality(Modality.APPLICATION_MODAL);

                        // Afficher la nouvelle fenêtre
                        stage.show();

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
        try {
            // Charger la page d'accueil (remplace "Accueil.fxml" par le nom correct)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
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


