package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.BO.Etudiant;
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
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class ListeEtudiantController extends MenuController implements Initializable {
    @FXML
    private Button bRetour;
    @FXML
    private TableView<Etudiant> tvEtudiants;
    @FXML
    private TableColumn<Etudiant, Void> tcActions;
    @FXML
    private TableColumn<Etudiant, String> tcNomEtud;
    @FXML
    private TableColumn<Etudiant, String> tcPrenomEtud;
    @FXML
    private TableColumn<Etudiant, String> tcDateNaissance;
    @FXML
    private TableColumn<Etudiant, String> tcSection;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        EtudiantDAO etudDAO = new EtudiantDAO();
        List<Etudiant> mesEtud = etudDAO.findAll();
        ObservableList<Etudiant> mesEtudOL = FXCollections.observableArrayList(mesEtud);

        tcNomEtud.setCellValueFactory(cellData -> cellData.getValue().nomEtudiantProperty());
        tcPrenomEtud.setCellValueFactory(cellData -> cellData.getValue().prenomEtudiantProperty());
        tcDateNaissance.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDateNaissance().format(formatter)));
        tcSection.setCellValueFactory(cellData -> {
            int idSection = cellData.getValue().getIdSection();
            String libelle = new SectionDAO().find(idSection).getLibelleSection();
            return new SimpleStringProperty(libelle);
        });

        tvEtudiants.setItems(mesEtudOL);

        Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btnModifier = new Button("Modifier");
            private final Button btnSupprimer = new Button("Supprimer");

            {
                btnModifier.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                btnSupprimer.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                btnModifier.setOnAction(event -> {
                    try {
                        // Fermer la fenêtre actuelle
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentStage.close();

                        // Charger le fichier FXML
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modifier_etudiant.fxml"));
                        Parent root = fxmlLoader.load();

                        // Obtenir le contrôleur de la nouvelle fenêtre
                        ModifierEtudiantController modifierEtudiantController = fxmlLoader.getController();
                        Etudiant etu = getTableView().getItems().get(getIndex());
                        modifierEtudiantController.setEtudiant(etu);

                        // Créer une nouvelle fenêtre (Stage)
                        Stage stage = new Stage();
                        stage.setTitle("Modifier un étudiant");
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
                    Etudiant etudiant = getTableView().getItems().get(getIndex());
                    Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmAlert.setTitle("Confirmation");
                    confirmAlert.setHeaderText(null);
                    confirmAlert.setContentText("Voulez-vous vraiment supprimer l'etudiant " + etudiant.getNomEtudiant() + " ?");

                    confirmAlert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            EtudiantDAO dao = new EtudiantDAO();
                            boolean deleted = dao.delete(etudiant);
                            if (deleted) {
                                getTableView().getItems().remove(etudiant);
                                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                successAlert.setTitle("Succes");
                                successAlert.setHeaderText(null);
                                successAlert.setContentText("Etudiant supprime.");
                                successAlert.showAndWait();
                            } else {
                                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                errorAlert.setTitle("Erreur");
                                errorAlert.setHeaderText(null);
                                errorAlert.setContentText("Echec de la suppression de l'etudiant.");
                                errorAlert.showAndWait();
                            }
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(10, btnModifier, btnSupprimer));
                }
            }
        };

        tcActions.setCellFactory(cellFactory);
        tvEtudiants.setItems(mesEtudOL);


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