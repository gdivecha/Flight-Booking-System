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
import javafx.stage.Stage;

/**
 *
 * @author ethan
 */
public class testFX extends Application {

   

    public void ownerScreen(Stage primaryStage) {
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

                while (in.hasNext()) {
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
        bookName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));
        TableColumn bookPrice = new TableColumn("Price");

        bookPrice.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("price"));
        table.getColumns().addAll(bookName, bookPrice);
       
        
        Label oLabel = new Label("Welcome Administrator:");
        TextField tfn = new TextField();

        TextField tfp = new TextField();

        
         Button backbtn = new Button("Back");                          // Back Button
        backbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cStartScreen(primaryStage);
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
                    // wikiWriter.write(new wikiPage(curpage + 1, tempName, tempSumm, tempDesc).toString());
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void cStartScreen(Stage primaryStage){
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
        System.out.println(Inventory.getBookInvList().get(0).getName());

        Label wLabel = new Label("Welcome, You have X points");

        Button lbtn = new Button();
        lbtn.setText("Logout");
        lbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logging Out");
            }
        });

        TableView<Book> table = new TableView<Book>();
        table.setEditable(true);

        TableColumn<Book, String> NameCol = new TableColumn("Book Name");
        NameCol.setMinWidth(300);
        //
        NameCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("name"));

        final ObservableList<Book> data = FXCollections.observableArrayList(Inventory.getBookInvList());
        System.out.println(data);
        TableColumn<Book, Double> PriceCol = new TableColumn("Price");
        PriceCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("price"));
        PriceCol.setMinWidth(50);
        table.setItems(null);

        table.setItems(data);
        table.getColumns().addAll(NameCol, PriceCol);
        System.out.println(NameCol.getCellData(0)); //this does not.
        primaryStage.setTitle("MyBookStoreViewer");

        Button bbtn = new Button();
        bbtn.setText("Buy");
        bbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Buying");
                Book purchasedBook = table.getSelectionModel().getSelectedItem();
                System.out.println("Purchasing Book " + purchasedBook.getName() + " For " + purchasedBook.getPrice());
                inv.deleteBook(purchasedBook);

                //UPDATE THE FILE.
                try {
                    FileWriter Bw = new FileWriter("Books.txt");
                    for (Book c : Inventory.getBookInvList()) {             //REWRITE THE BOOKS FILE BASED ON CHANGES THAT HAVE BEEN MADE.
                        Bw.write(c.getName() + "]" + c.getPrice() + "\n");
                    }
                    // wikiWriter.write(new wikiPage(curpage + 1, tempName, tempSumm, tempDesc).toString());
                    Bw.close();
                } catch (IOException E) {
                    System.out.println("ERROR HAS OCCURED BUYING A BOOK: Error Contents : \n" + E);
                }

                
                
                
                
            }
        });
        Button rbtn = new Button();
        rbtn.setText("Redeem");
        rbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Redeeming");
                Book purchasedBook = table.getSelectionModel().getSelectedItem();
                System.out.println("Purchasing Book " + purchasedBook.getName() + " For " + purchasedBook.getPrice() + " Using Points");
                inv.deleteBook(purchasedBook);
                try {
                    FileWriter Bw = new FileWriter("Books.txt");
                    for (Book c : Inventory.getBookInvList()) {             //REWRITE THE BOOKS FILE BASED ON CHANGES THAT HAVE BEEN MADE.
                        Bw.write(c.getName() + "]" + c.getPrice() + "\n");
                    }
                    // wikiWriter.write(new wikiPage(curpage + 1, tempName, tempSumm, tempDesc).toString());
                    Bw.close();
                } catch (IOException E) {
                    System.out.println("ERROR HAS OCCURED REDEEMING A BOOK: Error Contents : \n" + E);
                }
                //Note the book
                //Delete the book
                //rewrite the books file. 
                //move to receipt page.
                
                
                /*
                user.ShoppingCart.addBook(purchasedBook);
                user.ShoppingCart.redeemAndBuyBooks();
                 */
                
                
                

            }
        });

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        //  vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(wLabel, table, bbtn, rbtn, lbtn);

        Scene scene = new Scene(vbox);
        //  ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }

    public void start(Stage primaryStage) {
       ownerScreen(primaryStage);
        primaryStage.setTitle("OwnerBookScreen");
        /**
         *
         */
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
