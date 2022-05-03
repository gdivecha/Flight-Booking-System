package coe528groupproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author ethan
 */
public class CustomerStartScreen extends Application {

    // private TableView<Book> table = new TableView<Book>();
    @Override
    public void start(Stage primaryStage) {
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

        //ObservableList<Book> data = FXCollections.observableArrayList();
        // data.add(new Book("Cooking for Dummies", 10));
        //  data.add(new Book("Baking for Dummies", 10));
        //  StackPane root = new StackPane();
        //   root.getChildren().add(btn);
        TableView<Book> table = new TableView<Book>();
        table.setEditable(true);

        TableColumn<Book, String> NameCol = new TableColumn("Book Name");
        NameCol.setMinWidth(300);
        //
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        final ObservableList<Book> data = FXCollections.observableArrayList(Inventory.getBookInvList());
        System.out.println(data);
        // table.setItems(FXCollections.observableArrayList(Inventory.getBookInvList()));
        TableColumn<Book, Double> PriceCol = new TableColumn("Price");
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
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

                //Note the book
                //Delete the book
                //rewrite the books file. 
                //move to receipt page.
                
                
                
                /*
                user.ShoppingCart.addBook(purchasedBook);
                user.ShoppingCart.buyBooks();
                 */
                
                
                
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
