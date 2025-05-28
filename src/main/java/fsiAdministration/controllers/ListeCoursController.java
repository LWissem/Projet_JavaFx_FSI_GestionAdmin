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

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });



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
