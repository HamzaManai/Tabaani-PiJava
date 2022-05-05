/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import entities.Hebergement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class HebergementController implements Initializable {

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
}
