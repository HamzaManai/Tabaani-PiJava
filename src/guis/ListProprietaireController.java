/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

import entities.Proprietaire;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.shaded.apache.poi.hssf.usermodel.HSSFRow;
import org.shaded.apache.poi.hssf.usermodel.HSSFSheet;
import org.shaded.apache.poi.hssf.usermodel.HSSFWorkbook;
import services.ServiceProprietaire;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author HPOMEN-I7-1TR
 */
public class ListProprietaireController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableColumn<Proprietaire, String> nom_prop;
    @FXML
    private TableColumn<Proprietaire, String> prenom_prop;
    @FXML
    private TableColumn<Proprietaire, String> email_prop;
    @FXML
    private TableColumn<Proprietaire, String> num_tlf_prop;
    @FXML
    private TableColumn<Proprietaire, String> img_prop;
    @FXML
    private TableColumn<Proprietaire, Void> modifier;
    @FXML
    private TableColumn<Proprietaire, Void> supprimer;
    @FXML
    private TableView<Proprietaire> ListProprietaires;

    String path = "D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\uploads\\";

    ServiceProprietaire sp = new ServiceProprietaire();

    ObservableList<Proprietaire> list = FXCollections.observableArrayList(
            sp.getAll()
    );
    @FXML
    private MenuButton menuButton;
    @FXML
    private MenuItem trierNom;
    @FXML
    private MenuItem tierDate;
    @FXML
    private MenuItem trierEmail;
    ObservableList<Proprietaire> listP;
    @FXML
    private AnchorPane myAnchorPane;
    @FXML
    private Button btnExcel;
    @FXML
    private Button btnPdf1;
    @FXML
    private Button hebergement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Callback<TableColumn<Proprietaire, Void>, TableCell<Proprietaire, Void>> cellfactorydelete = (param) -> {
            final TableCell<Proprietaire, Void> cell = new TableCell<Proprietaire, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button deletebutton = new Button("Delete");
                        deletebutton.setOnAction(event -> {
                            Stage stage = (Stage) myAnchorPane.getScene().getWindow();

                            Alert.AlertType type = Alert.AlertType.CONFIRMATION;
                            Alert alert = new Alert(type, "");
                            alert.initModality(Modality.APPLICATION_MODAL);
                            alert.initOwner(stage);
                            alert.getDialogPane().setContentText("Voulez vous vraiment supprimer cette Proprietaire?");
                            alert.getDialogPane().setHeaderText("Suppression");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                Proprietaire p = getTableView().getItems().get(getIndex());
                                sp.supprimer(p.getId());
                                list.remove(p);
                                refresh();

                                Notifications notificationBuilder = Notifications.create()
                                        .title("Suppression effectuée")
                                        .text("Proprietaire supprimée")
                                        .graphic(null)
                                        .hideAfter(Duration.seconds(3))
                                        .position(Pos.TOP_RIGHT)
                                        .onAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                //System.out.println("Supp");
                                            }
                                        });
                                notificationBuilder.showConfirm();
                            } else if (result.get() == ButtonType.CANCEL) {
                                //nothing
                            }

                        });

                        setGraphic(deletebutton);
                        setText(null);

                    }

                }
            ;

            };
            
            return cell;
        };

        Callback<TableColumn<Proprietaire, Void>, TableCell<Proprietaire, Void>> cellfactoryedit = (param) -> {
            final TableCell<Proprietaire, Void> cell = new TableCell<Proprietaire, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button editbutton = new Button("Edit");
                        editbutton.setOnAction(event -> {
                            Proprietaire p = getTableView().getItems().get(getIndex());
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProprietaire.fxml"));
                                Parent second = loader.load();

                                ModifierProprietaireController secondcontroller = loader.<ModifierProprietaireController>getController();
                                secondcontroller.setTfNom_prop(p.getNom_prop());
                                secondcontroller.setTfPrenom_prop(p.getPrenom_prop());
                                secondcontroller.setTfEmail_prop(p.getEmail_prop());
                                secondcontroller.setTfNum_tlf_pro(String.valueOf(p.getNum_tlf_prop()));
                                secondcontroller.setImg_prop(p.getImg_prop());
                                secondcontroller.setA(p);

                                Scene s = new Scene(second);
                                Stage stageedit = new Stage();
                                stageedit.setScene(s);

                                stageedit.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ListTypeHebergementController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        setGraphic(editbutton);
                        setText(null);

                    }

                }
            ;

            };
            
            return cell;
        };

        modifier.setCellFactory(cellfactoryedit);
        supprimer.setCellFactory(cellfactorydelete);
        nom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("nom_prop"));
        prenom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("prenom_prop"));
        email_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("email_prop"));
        num_tlf_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("num_tlf_prop"));

        ImageView iv = new ImageView(new Image("file:///" + path + new PropertyValueFactory<Proprietaire, String>("img_prop")));

        img_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("img_prop"));
        ListProprietaires.setItems(list);
    }

    @FXML
    private void MenuProprietaire(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("MenuProprietaire.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void Refresh(MouseEvent event) {
        nom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("nom_prop"));
        prenom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("prenom_prop"));
        email_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("email_prop"));
        num_tlf_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("num_tlf_prop"));
        img_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("img_prop"));
        ListProprietaires.setItems(list);
    }

    private void refresh() {
        list.clear();
        list = (ObservableList<Proprietaire>) sp.getAll();
        System.out.println(list);
        ListProprietaires.setItems(list);

    }

    @FXML
    private void trierNom(ActionEvent event) {
        nom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("nom_prop"));
        prenom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("prenom_prop"));
        email_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("email_prop"));
        num_tlf_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("num_tlf_prop"));
        img_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("img_prop"));
        list = sp.getAllTriNom();
        ListProprietaires.setItems(list);
    }

    @FXML
    private void trierPrenom(ActionEvent event) {
        nom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("nom_prop"));
        prenom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("prenom_prop"));
        email_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("email_prop"));
        num_tlf_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("num_tlf_prop"));
        img_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("img_prop"));
        list = sp.getAllTriPrenom();
        ListProprietaires.setItems(list);
    }

    @FXML
    private void trierEmail(ActionEvent event) {
        nom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("nom_prop"));
        prenom_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("prenom_prop"));
        email_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("email_prop"));
        num_tlf_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("num_tlf_prop"));
        img_prop.setCellValueFactory(new PropertyValueFactory<Proprietaire, String>("img_prop"));
        list = sp.getAllTriEmail();
        ListProprietaires.setItems(list);
    }

    @FXML
    private void Trier(ActionEvent event) {
    }

    @FXML
    private void pdf(ActionEvent event) {
        Document doc = new Document();
        String FILE_NAME = "E:\\java_pdf\\chillyfacts.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\props.pdf"));
            doc.open();
            Paragraph p = new Paragraph();
            p.add("Liste de Proprietaires");
            p.setAlignment(Element.ALIGN_CENTER);

            doc.add(p);
            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(20f);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Nom"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Prenom"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Email"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Telephone"));

            cell1.setBackgroundColor(BaseColor.GREEN);
            cell2.setBackgroundColor(BaseColor.GREEN);
            cell3.setBackgroundColor(BaseColor.GREEN);
            cell4.setBackgroundColor(BaseColor.GREEN);
            cell1.setPadding(5);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);

            listP = FXCollections.observableArrayList(
                    sp.getAll()
            );
            //listP =  (ObservableList<Proprietaire>) sp.getAll();
            for (int i = 0; i < listP.size(); i++) {
                String nom = listP.get(i).getNom_prop();
                String prenom = listP.get(i).getPrenom_prop();
                String email = listP.get(i).getEmail_prop();
                Integer num = listP.get(i).getNum_tlf_prop();
                table.addCell(nom);
                table.addCell(prenom);
                table.addCell(email);
                table.addCell(String.valueOf(num));

            }
            doc.add(table);

            Desktop.getDesktop().open(new File("D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\props.pdf"));

            doc.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListProprietaireController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ListProprietaireController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListProprietaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Excel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        /*
        ExcelFile file = new ExcelFile();
        ExcelWorksheet worksheet = file.addWorksheet("sheet");
        for (int row = 0; row < ListProprietaires.getItems().size(); row++) {
            ObservableList cells = (ObservableList) ListProprietaires.getItems().get(row);
            for (int column = 0; column < cells.size(); column++) {
                if (cells.get(column) != null) {
                    worksheet.getCell(row, column).setValue(cells.get(column).toString());
                }
            }
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
        );
        File saveFile = fileChooser.showSaveDialog(ListProprietaires.getScene().getWindow());

        file.save(saveFile.getAbsolutePath());
         */

        Connection cn = DataSource.getInstance().getCnx();
        String query = "Select * from proprietaire";
        PreparedStatement pst = cn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Liste Proprietaires");
        HSSFRow header = sheet.createRow(0);

        header.createCell(1).setCellValue("Nom");
        header.createCell(2).setCellValue("Prenom");
        header.createCell(3).setCellValue("Email");
        header.createCell(4).setCellValue("Telephone");

        int index = 1;
        while (rs.next()) {
            HSSFRow row = sheet.createRow(index);

            row.createCell(1).setCellValue(rs.getString("nom_prop"));
            row.createCell(2).setCellValue(rs.getString("prenom_prop"));
            row.createCell(3).setCellValue(rs.getString("email_prop"));
            row.createCell(4).setCellValue(rs.getString("num_tlf_prop"));

            index++;
        }

        FileOutputStream file = new FileOutputStream("D:\\Esprit\\pidev\\Java\\Essay1Java\\src\\props.xls");
        wb.write(file);
        file.close();

        //JOptionPane.showMessageDialog(null, "Exportation 'EXCEL' effectuée avec succés");
        Notifications notificationBuilder = Notifications.create()
                .title("Excel")
                .text("Exportation 'EXCEL' effectuée avec succés")
                .graphic(null)
                .hideAfter(Duration.seconds(3))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //System.out.println("Supp");
                    }
                });
        notificationBuilder.showConfirm();
        pst.close();
        rs.close();

    }

    @FXML
    private void hebergementMenu(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuBack.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void hebergementMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuBack.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
