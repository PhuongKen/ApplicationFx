/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFX;

import entity.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ProductModel;

/**
 *
 * @author Phuong
 */
public class FormProduct extends Application {

    private Scene scene1;
    private Scene scene2;
    private Stage window;

    ProductModel model = new ProductModel();
    TableView<Product> tableView = new TableView<>();
    ObservableList<Product> list = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        GridPane pane1 = new GridPane();
        pane1.setPadding(new Insets(20, 20, 20, 20));
        pane1.setVgap(15);
        pane1.setHgap(20);

        Label lblName = new Label("Name");
        Label lblImage = new Label("Image");
        Label lblPrice = new Label("Price");

        TextField txtName = new TextField();
        TextField txtImage = new TextField();
        TextField txtPrice = new TextField();
        //table view
        TableColumn<Product, String> columnName = new TableColumn<>("Name");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnName.setMinWidth(100);

        TableColumn<Product, String> columnImage = new TableColumn<>("Image");
        columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        columnImage.setMinWidth(100);

        TableColumn<Product, String> columnPrice = new TableColumn<>("Price");
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnPrice.setMinWidth(100);
        
        TableColumn<Product, String> columnAction = new TableColumn<>("Action");
        columnAction.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnAction.setMinWidth(100);

        columnImage.setCellFactory(new Callback<TableColumn<Product, String>, TableCell<Product, String>>() {
            @Override
            public TableCell<Product, String> call(TableColumn<Product, String> param) {
                return new TableCell<Product, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        if (item != null) {
                            HBox box = new HBox();
                            box.setSpacing(10);
                            VBox vbox = new VBox();
                            ImageView imageView = new ImageView();
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                            imageView.setImage(new Image("resource/Code.jpg"));
                            box.getChildren().addAll(imageView, vbox);
                            setGraphic(box);
                        }
                    }
                };
            }

        });

        tableView.getColumns().addAll(columnImage, columnName, columnPrice,columnAction);

        //
        Button submit = new Button("Submit");
        submit.setOnAction((ActionEvent event) -> {
            String name = txtName.getText();
            String image = txtImage.getText();
            String price = txtPrice.getText();
            Product p = new Product(name, image, Integer.parseInt(price));
            model.save(p);
            System.out.println("Lưu thành công.");
            model.query(new Product(name, image, 0));
            list.addAll(p);
            tableView.getItems().setAll(list);
            window.setScene(scene2);
        });
        Button reset = new Button("Reset");

        reset.setOnAction((event) -> {
            txtName.clear();
            txtImage.clear();
            txtPrice.clear();
        });

        GridPane.setConstraints(lblName, 2, 2);
        GridPane.setConstraints(lblImage, 2, 3);
        GridPane.setConstraints(lblPrice, 2, 4);

        GridPane.setConstraints(txtName, 3, 2);
        GridPane.setConstraints(txtImage, 3, 3);
        GridPane.setConstraints(txtPrice, 3, 4);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(submit, reset);
        GridPane.setConstraints(hBox, 3, 5);

        pane1.getChildren().addAll(lblName, lblImage, lblPrice, txtName, txtImage, txtPrice, hBox);
        pane1.setAlignment(Pos.CENTER);

        Product p = new Product();

        scene2 = new Scene(tableView, 400, 300);

        scene1 = new Scene(pane1, 450, 280);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
