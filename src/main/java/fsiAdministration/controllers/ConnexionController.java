package fsiAdministration.controllers;

import fsiAdministration.BO.Utilisateur;
import fsiAdministration.DAO.UtilisateurDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
<<<<<<< HEAD
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
=======
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

<<<<<<< HEAD
public class ConnexionController implements javafx.fxml.Initializable {

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField tfMDP;

=======
public class ConnexionController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField tfLogin;
    @FXML
    private TextField tfMDP;
>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
    @FXML
    private Button bConnexion;

    @FXML
<<<<<<< HEAD
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
=======
    public void bConnexionClick(ActionEvent event) {
        String login = tfLogin.getText();
        String mdp = tfMDP.getText();

        System.out.println(login);

>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d

        UtilisateurDAO userDAO = new UtilisateurDAO();
        Utilisateur user = userDAO.find(login, mdp);

        if (user != null) {
            showAccueil();
<<<<<<< HEAD
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

            Stage stage = new Stage();
            stage.setTitle("Accueil FSI ADMINISTRATION");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
=======
        }
    }

    private void showAccueil(){
         Stage stageP = (Stage) bConnexion.getScene().getWindow();
         //on ferme l'écran
          stageP.close();
          try {

                // Charger le fichier FXML pour la pop-up
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
                Parent root = fxmlLoader.load();

                // Obtenir le contrôleur de la nouvelle fenetre
                AccueilController accueilController = fxmlLoader.getController();

                // Créer une nouvelle fenêtre (Stage)
                Stage stage = new Stage();
                stage.setTitle("Accueil FSI ADMINISTRATION");
                stage.setScene(new Scene(root));

                // Configurer la fenêtre en tant que modal
                stage.initModality(Modality.APPLICATION_MODAL);

                // Afficher la fenêtre et attendre qu'elle se ferme
                stage.show();


            } catch (Exception e) {
                e.printStackTrace();
            }
    }

>>>>>>> 65433509a2f9d659798cc33ba5b57cd2a9f6a87d
}
