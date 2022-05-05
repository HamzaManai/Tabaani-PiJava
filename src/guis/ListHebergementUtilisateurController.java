/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Hebergement;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import services.ServiceHebergement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.*;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class ListHebergementUtilisateurController implements Initializable {

    private AnchorPane rootpane;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    private Image image;
    private MyListener myListener;
    String path = "D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\uploads\\";

    public static final String ACCOUNT_SID = "AC67542ea544970eafa3d1434ce0783af1";
    public static final String AUTH_TOKEN = "725e7ce80abc8cd4e13f23880946fc86";

    Hebergement hbrg;

    ServiceHebergement sh = new ServiceHebergement();

    private ObservableList<Hebergement> getTableList() {

        ObservableList<Hebergement> List = (ObservableList<Hebergement>) sh.list();
        return List;
    }

    ObservableList<Hebergement> list = FXCollections.observableArrayList(
            sh.list()
    );
    @FXML
    private Label lbType;
    @FXML
    private Label lbAdresse;
    @FXML
    private Label lbPlaces;
    @FXML
    private TextField tfsearch;

    private void setChosenHebergement(Hebergement h) {
        this.hbrg = h;

        fruitNameLable.setText(h.getNom_hbrg());
        fruitPriceLabel.setText(h.getPrix_hbrg() + "TND");
        //lbType.setText(h.getType().getNom_type_hbrg());
        lbAdresse.setText(h.getAdresse_hbrg());
        lbPlaces.setText(String.valueOf(h.getNbr_place_hbrg()));
        Image im = new Image("file:///" + path + h.getImg_hbrg());
        //ImageView iv = new ImageView(im);
        // im.setFitHeight(112);
        // im.setFitWidth(200);
        fruitImg.setImage(im);
        //   image = new Image(path+h.getImg_hbrg());
        //(file.toURI().toString()));
        //fruitImg.setImage(new Image (path+h.getImg_hbrg()) );
        //chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
        //    "    -fx-background-radius: 30;");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // list.addAll(getData());
        if (list.size() > 0) {
            setChosenHebergement(list.get(0));
            myListener = new MyListener() {

                @Override
                public void onClickListener(Hebergement h) {
                    setChosenHebergement(h);
                }

            };
        }
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < list.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Hebergement.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                HebergementController HebergementController = fxmlLoader.getController();
                HebergementController.setData(list.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void reserverHbrg(ActionEvent event) throws Exception {

        sh.ReservationHebergement(hbrg);
        hbrg.setNbr_place_hbrg(hbrg.getNbr_place_hbrg() - 1);
        lbPlaces.setText(String.valueOf(hbrg.getNbr_place_hbrg()));
        sh.ModifierNbrPlace(hbrg);
        
        /*
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber("+21652702422"),
                new PhoneNumber("+19126423767"),
                "Votre Reservtion est effectuee ").create();
        System.out.println(message.getSid());
         */

        //JavaMail.sendMail("manai.hamza@esprit.tn");
        JavaMail s = new JavaMail();
        s.mail();
        
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Reservatoion");
        tray.setMessage("reservation done ! un sms et un email sont envoyees ");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

        list = null;
        list = (ObservableList<Hebergement>) sh.list();

    }

    FilteredList<Hebergement> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<Hebergement> sort = new SortedList<>(filter);

    @FXML
    private void search(ActionEvent event) {

        tfsearch.setOnKeyReleased(e -> {

            tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(t -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(t.getNom_hbrg()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            //   sort.comparatorProperty().bind(grid.comparatorProperty());
            //  tbBlog.setItems(sort);
        });
    }

    @FXML
    private void AjouterHbrg(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterHebergementUtilisateur.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void hbrgUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HebergementUtilisateur.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
