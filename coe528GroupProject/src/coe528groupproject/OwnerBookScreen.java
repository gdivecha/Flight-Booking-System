/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528groupproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author ethan
 */
public class OwnerBookScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
      
        
          TableView<Book> table = new TableView<Book>();
        Inventory inv = Inventory.getInvInstance();
        String tempName;
        double tempPrice;
//IMPORT
        try {
            File dbfile = new File("Books.txt");
            if (dbfile.createNewFile()) {
                //true if file doesnt exist and file was created
                System.out.println("FILE CREATED SUCCESSFULLY");
            } else {
                //File exists, read from it.
                System.out.println("FILE ALREADY EXISTS");
                Scanner in = new Scanner(dbfile);
                String iFContents = "";
//books have a name and a price

                while (in.hasNext()) {
                    // System.out.println("READING");
                    iFContents = iFContents + in.nextLine();
                    iFContents = iFContents + "\n";
                }
                StringTokenizer tokenizer = new StringTokenizer(iFContents, "]\n");
                while (tokenizer.hasMoreTokens()) {
                    tempName = tokenizer.nextToken();
                    tempPrice = Double.parseDouble(tokenizer.nextToken());
                    Book c = new Book(tempName, tempPrice);
                    inv.addBook(c);
                }

            }
        } catch (IOException e) {
            System.out.println(e);

        }
         ObservableList<Book> data = FXCollections.observableArrayList(Inventory.getBookInvList());
        //  System.out.println(data.get(0).getName());
        TableColumn bookName = new TableColumn("Name");
        bookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn bookPrice = new TableColumn("Price");

        bookPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().addAll(bookName, bookPrice);
       
        
        Label oLabel = new Label("Welcome Administrator:");
        TextField tfn = new TextField();

        TextField tfp = new TextField();

        
         Button backbtn = new Button("Back");                          // Back Button
        backbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                //GO TO OWNER START SCREEN
            }
        });
        
        
        
         HBox aAndb = new HBox();
        aAndb.getChildren().addAll(oLabel, backbtn);
        
        
        
        
        Button abtn = new Button("Add");                          //ADD BUTTON
        abtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(tfn.getText()); //returns the text entered in the textfield
                Book c = new Book(tfn.getText(), Double.parseDouble(tfp.getText()));
                inv.addBook(c);
                
                  try {
                    FileWriter Bw = new FileWriter("Books.txt");
                    for (Book A : Inventory.getBookInvList()) {             //REWRITE THE BOOKS FILE BASED ON CHANGES THAT HAVE BEEN MADE.
                        Bw.write(A.getName() + "]" + A.getPrice() + "\n");
                    }
                    Bw.close();
                } catch (IOException E) {
                    System.out.println("ERROR HAS OCCURED BUYING A BOOK: Error Contents : \n" + E);
                }
                      ObservableList<Book> upData = FXCollections.observableArrayList(Inventory.getBookInvList());
                  table.setItems(upData);
                  primaryStage.show();
            }
        });

        
        
        
        
        Button rbtn = new Button("Remove");
        rbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                  System.out.println("Removing");
                Book purchasedBook = table.getSelectionModel().getSelectedItem();
                System.out.println("Removing Book " + purchasedBook.getName() + " That Costs " + purchasedBook.getPrice() );
                inv.deleteBook(purchasedBook);
                try {
                    FileWriter Bw = new FileWriter("Books.txt");
                    for (Book c : Inventory.getBookInvList()) {             //REWRITE THE BOOKS FILE BASED ON CHANGES THAT HAVE BEEN MADE.
                        Bw.write(c.getName() + "]" + c.getPrice() + "\n");
                    }
                    Bw.close();
                } catch (IOException E) {
                    System.out.println("ERROR HAS OCCURED REDEEMING A BOOK: Error Contents : \n" + E);
                }
              ObservableList<Book> upData = FXCollections.observableArrayList(Inventory.getBookInvList());
                  table.setItems(upData);
                  primaryStage.show();

            }
        });
        table.setItems(data);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        HBox hbox = new HBox();
        aAndb.setSpacing(10);
        hbox.setSpacing(10);
        hbox.getChildren().addAll(tfn, tfp);

        //  vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(aAndb, table, hbox, abtn, rbtn);

        Scene scene = new Scene(vbox);

        primaryStage.setTitle("OwnerBookScreen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
