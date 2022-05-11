/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import entities.Hebergement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceHebergement;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class Hebergement2Controller implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;

    private Hebergement hebergement;
    private MyListener myListener;
    String path = "D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\uploads\\";
    @FXML
    private Label nameLabel1;
    @FXML
    private Label places;
    @FXML
    private Label nameLabel11;
    @FXML
    private Label type;
    @FXML
    private Label nameLabel12;
    @FXML
    private Label adresse;
    @FXML
    private Label nameLabel122;
    @FXML
    private Label date;

    private Stage stage;
    private Scene scene;
    private Parent root;

    ServiceHebergement sh = new ServiceHebergement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(hebergement);
    }

    public void setData(Hebergement hebergement, MyListener myListener) {
        this.hebergement = hebergement;
        this.myListener = myListener;
        nameLabel.setText(hebergement.getNom_hbrg());
        priceLable.setText(hebergement.getPrix_hbrg() + "TND");
        Image im = new Image("file:///" + path + hebergement.getImg_hbrg());

        //   Image image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
        img.setImage(im);
        img.setFitHeight(112);
        img.setFitWidth(200);
        places.setText(String.valueOf(hebergement.getNbr_place_hbrg()));
        type.setText(hebergement.type.toString());
        adresse.setText(hebergement.getAdresse_hbrg());
        date.setText(String.valueOf(hebergement.getDate_hbrg()));

    }

    @FXML
    private void edit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierHebergementUtilisateur.fxml"));
        Parent second = loader.load();

        ModifierHebergementUtilisateurController secondcontroller = loader.<ModifierHebergementUtilisateurController>getController();
        secondcontroller.setH(hebergement);
        secondcontroller.setTfNom_hbrg(hebergement.getNom_hbrg());
        secondcontroller.setTfAdresse_hbrg(hebergement.getNom_hbrg());
        secondcontroller.setDpDate_hbrg(hebergement.getDate_hbrg());
        secondcontroller.setTfPrix_hbrg(String.valueOf(hebergement.getPrix_hbrg()));
        secondcontroller.setTfNbr_place_hbrg(String.valueOf(hebergement.getNbr_place_hbrg()));
        //secondcontroller.setCbType_hbrg(hebergement.getType());
        secondcontroller.setImg_hbrg(hebergement.getImg_hbrg());

        Scene s = new Scene(second);
        Stage stageedit = new Stage();
        stageedit.setScene(s);
        stageedit.show();

    }

    @FXML
    private void supprimer(ActionEvent event) {
        sh.supprimer(hebergement.getId());

    }
}
