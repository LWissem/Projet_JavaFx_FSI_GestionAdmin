package fsiAdministration.controllers;

import fsiAdministration.BO.Utilisateur;
import fsiAdministration.DAO.UtilisateurDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnexionController implements javafx.fxml.Initializable {

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfMDP;

    @FXML
    private Button bConnexion;

    @FXML
    private Label loginError;

    @FXML
    private Label mdpError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // cacher les erreurs au démarrage
        loginError.setVisible(false);
        mdpError.setVisible(false);
    }

    @FXML
    public void bConnexionClick(ActionEvent event) {
        String login = tfLogin.getText().trim();
        String mdp = tfMDP.getText().trim();

        boolean hasError = false;

        // Réinitialiser messages d’erreur
        loginError.setText("");
        loginError.setVisible(false);
        mdpError.setText("");
        mdpError.setVisible(false);

        if (login.isEmpty()) {
            loginError.setText("Veuillez entrer votre identifiant.");
            loginError.setVisible(true);
            hasError = true;
        }

        if (mdp.isEmpty()) {
            mdpError.setText("Veuillez entrer votre mot de passe.");
            mdpError.setVisible(true);
            hasError = true;
        }

        if (hasError) return;

        UtilisateurDAO userDAO = new UtilisateurDAO();
        Utilisateur user = userDAO.find(login, mdp);

        if (user != null) {
            showAccueil();
        } else {
            mdpError.setText("Identifiants incorrects.");
            mdpError.setVisible(true);
        }
    }

    private void showAccueil() {
        Stage stageP = (Stage) bConnexion.getScene().getWindow();
        stageP.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
            Parent root = fxmlLoader.load();

            AccueilController accueilController = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.setTitle("Accueil FSI ADMINISTRATION");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
