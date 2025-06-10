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
<<<<<<< HEAD
import javafx.scene.Node;
=======
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeEtudiantController extends MenuController implements Initializable {
<<<<<<< HEAD
    // TableView et ses colonnes pour afficher les étudiants
=======
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

<<<<<<< HEAD
        // Récupération des étudiants depuis la base
        EtudiantDAO etudDAO = new EtudiantDAO();
        List<Etudiant> mesEtud = etudDAO.findAll();

        // Conversion en ObservableList pour la TableView
        ObservableList<Etudiant> mesEtudOL = FXCollections.observableArrayList(mesEtud);

        // Lier chaque colonne à une propriété de l'objet Etudiant
=======

        EtudiantDAO etudDAO = new EtudiantDAO();
        List<Etudiant> mesEtud = etudDAO.findAll();
        ObservableList<Etudiant> mesEtudOL= FXCollections.observableArrayList(mesEtud);

>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
        tcNomEtud.setCellValueFactory(cellData -> cellData.getValue().nomEtudiantProperty());
        tcPrenomEtud.setCellValueFactory(cellData -> cellData.getValue().prenomEtudiantProperty());
        tcDateNaissance.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDateNaissance().toString()));
<<<<<<< HEAD

        // Afficher le libellé de la section à partir de l'idSection
=======
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
        tcSection.setCellValueFactory(cellData -> {
            int idSection = cellData.getValue().getIdSection();
            String libelle = new SectionDAO().find(idSection).getLibelleSection();
            return new SimpleStringProperty(libelle);
        });

<<<<<<< HEAD
        // Remplir la table avec les données des étudiants
        tvEtudiants.setItems(mesEtudOL);

        // Création des boutons Modifier et Supprimer dans chaque ligne
=======
        tvEtudiants.setItems(mesEtudOL);

>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
        Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btnModifier = new Button("Modifier");
            private final Button btnSupprimer = new Button("Supprimer");

            {
                btnModifier.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                btnSupprimer.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                btnModifier.setOnAction(event -> {
                    try {
<<<<<<< HEAD
                        // Fermer la fenêtre actuelle
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentStage.close();
=======
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d

                        // Charger le fichier FXML
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modifier_etudiant.fxml"));
                        Parent root = fxmlLoader.load();

<<<<<<< HEAD
                        // Obtenir le contrôleur de la nouvelle fenêtre
=======

                        // Obtenir le contrôleur de la nouvelle fenetre
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
                        ModifierEtudiantController modifierEtudiantController = fxmlLoader.getController();
                        Etudiant etu = getTableView().getItems().get(getIndex());
                        modifierEtudiantController.setEtudiant(etu);

                        // Créer une nouvelle fenêtre (Stage)
                        Stage stage = new Stage();
                        stage.setTitle("Modifier un étudiant");
                        stage.setScene(new Scene(root));

                        // Configurer la fenêtre en tant que modal
                        stage.initModality(Modality.APPLICATION_MODAL);

<<<<<<< HEAD
                        // Afficher la nouvelle fenêtre
                        stage.show();
=======
                        // Afficher la fenêtre et attendre qu'elle se ferme
                        stage.showAndWait();
                        tvEtudiants.setItems(FXCollections.observableArrayList(new EtudiantDAO().findAll()));


>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

<<<<<<< HEAD

=======
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
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
<<<<<<< HEAD

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
=======
    @FXML
    public void bRetourClick(ActionEvent event) {
        Stage stage = (Stage) bRetour.getScene().getWindow();
        stage.close();
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
    }
}