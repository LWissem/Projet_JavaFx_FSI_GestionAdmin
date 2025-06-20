package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.DAO.EtudiantDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccueilController extends MenuController implements Initializable {

    @FXML
    private Label bvn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Session.utilisateurConnecte !=null){
            String login= Session.utilisateurConnecte.getLoginUtilisateur();
            bvn.setText("Bienvenue "+login);
        }
        else {
            bvn.setText("Bienvenue");
        }

    }
}
