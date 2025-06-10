    package fsiAdministration.controllers;

    import fsiAdministration.BO.Cours;
    import fsiAdministration.BO.Etudiant;
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

    public class ListeCoursController extends MenuController implements Initializable {
        @FXML
        private Button bRetour;
        @FXML
        private TableView<Cours> tvCours;

        @FXML
        private TableColumn<Cours, Void> tcActions;

        @FXML
        private TableColumn<Cours, String> tcLibelleCours;

        @FXML
        private TableColumn<Cours, String> tcDescriptionCours;
        @FXML
        private TableColumn<Cours, String> tcSection;


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            CoursDAO coursDAO = new CoursDAO();
            List<Cours> listeCours = coursDAO.findAll();
            ObservableList<Cours> mesCours = FXCollections.observableArrayList(listeCours);

            tcLibelleCours.setCellValueFactory(cellData -> cellData.getValue().libelleCoursProperty());
            tcDescriptionCours.setCellValueFactory(cellData -> cellData.getValue().descriptionCoursProperty());
            tcSection.setCellValueFactory(cellData -> {
                int idSection = cellData.getValue().getIdSection();
                String libelle = new SectionDAO().find(idSection).getLibelleSection();
                return new SimpleStringProperty(libelle);
            });


            tvCours.setItems(mesCours);

            Callback<TableColumn<Cours, Void>, TableCell<Cours, Void>> cellFactory = param -> new TableCell<>() {
                private final Button btnModifier = new Button("Modifier");
                private final Button btnSupprimer = new Button("Supprimer");

                {
                btnModifier.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                btnSupprimer.setStyle("-fx-background-color: red; -fx-text-fill: white;");

<<<<<<< HEAD
                    btnModifier.setOnAction(event -> {
                        try {
                            // Fermer la fenêtre actuelle
                            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            currentStage.close();

                            // Charger le fichier FXML
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modifier_cours.fxml"));
                            Parent root = fxmlLoader.load();

                            // Passer le cours sélectionné au contrôleur
                            ModifierCoursController modifierCoursController = fxmlLoader.getController();
                            Cours cours = getTableView().getItems().get(getIndex());
                            modifierCoursController.setCours(cours);

                            // Ouvrir une nouvelle fenêtre
                            Stage stage = new Stage();
                            stage.setTitle("Modifier un cours");
                            stage.setScene(new Scene(root));
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.show();
=======
                btnModifier.setOnAction(event -> {
                    try {

                    // Charger le fichier FXML
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modifier_cours.fxml"));
                    Parent root = fxmlLoader.load();


                    // Obtenir le contrôleur de la nouvelle fenetre
                    ModifierCoursController modifierCoursController = fxmlLoader.getController();
                    Cours cours = getTableView().getItems().get(getIndex());
                    modifierCoursController.setCours(cours);

                    // Créer une nouvelle fenêtre (Stage)
                    Stage stage = new Stage();
                    stage.setTitle("Modifier un cours");
                    stage.setScene(new Scene(root));

                    // Configurer la fenêtre en tant que modal
                    stage.initModality(Modality.APPLICATION_MODAL);

                    // Afficher la fenêtre et attendre qu'elle se ferme
                    stage.showAndWait();
                    tvCours.setItems(FXCollections.observableArrayList(new CoursDAO().findAll()));


>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

<<<<<<< HEAD


=======
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
                    btnSupprimer.setOnAction(event -> {
                        Cours cours = getTableView().getItems().get(getIndex());
                        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmAlert.setTitle("Confirmation");
                        confirmAlert.setHeaderText(null);
                        confirmAlert.setContentText("Voulez-vous vraiment supprimer le cours " + cours.getLibelleCours() + " ?");

                        confirmAlert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                CoursDAO dao = new CoursDAO();
                                boolean deleted = dao.delete(cours);
                                if (deleted) {
                                    getTableView().getItems().remove(cours);
                                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                    successAlert.setTitle("Succes");
                                    successAlert.setHeaderText(null);
                                    successAlert.setContentText("Cours supprime.");
                                    successAlert.showAndWait();
                                } else {
                                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                    errorAlert.setTitle("Erreur");
                                    errorAlert.setHeaderText(null);
                                    errorAlert.setContentText("Echec de la suppression du cours.");
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
            tvCours.setItems(mesCours);


        }

        @FXML
        public void bRetourClick(ActionEvent event) {
<<<<<<< HEAD
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
            Stage stage = (Stage) bRetour.getScene().getWindow();
            stage.close();
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
        }
    }
