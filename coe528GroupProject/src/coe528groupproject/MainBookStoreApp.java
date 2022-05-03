/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528groupproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author ethan
 */
public class MainBookStoreApp extends Application {

    Customer cCustomer = null;
    Book purchasedBook = null;

    @Override
    public void start(Stage primaryStage) {
        cCustomer = new Customer("Tester", "TesterPw");
        cCustomer.setPoints(0);
        cCustomer.setStatus();
        loginScreen(primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void loginScreen(Stage primaryStage) {
//        cCustomer = null;
        TextField usernameEnter;
        TextField passwordEnter;
        Owner owner = Owner.getInstance();
        
        // THE MISSING CODE!!!! : owner.getCustomerList().clear();
        
        String readUsername, readPassword;
        int readPoints;

        try {
            File userData = new File("customers.txt");
            if (userData.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File exists");
                Scanner in = new Scanner(userData);
                String ifContents = "";
                while (in.hasNext()) {
                    System.out.println("reading file");
                    ifContents += in.nextLine();
                    ifContents += "\n";
                }
                System.out.println("\n\n\n IFCONTENTS: \n" + ifContents + "\n\n\n");
                StringTokenizer tokenizer = new StringTokenizer(ifContents, "[\n");
                while (tokenizer.hasMoreTokens()) {

                    readUsername = tokenizer.nextToken();
                    System.out.println("UNTOKENS : " + readUsername);
                    readPassword = tokenizer.nextToken();
                    System.out.println("PWTOKENS : " + readPassword);
                    readPoints = Integer.parseInt(tokenizer.nextToken());
                    System.out.println("PTSTOKENS : " + readPoints);
                    //owner.addCustomer(readUsername, readPassword, readPoints);
                    Customer tempCust = new Customer(readUsername, readPassword);
                    tempCust.setPoints(readPoints);
                    tempCust.setStatus();
                    owner.addCustomer(tempCust);
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }

        //TextField for username Input
        usernameEnter = new TextField();
        usernameEnter.setMinWidth(70);
        usernameEnter.setPromptText("username");

        //TextField for password Input
        passwordEnter = new TextField();
        passwordEnter.setMinWidth(70);
        passwordEnter.setPromptText("password");

        //Label for welcome message
        Label welcomeMsg = new Label("Welcome to our bookstore!");
        //Login Button
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction((ActionEvent e) -> {
            System.out.println("Pressed login button");
            //Login for Owner
            if (usernameEnter.getText().equals("admin") && passwordEnter.getText().equals("admin")) {
                ownerStartScreen(primaryStage);
            }
            Customer tc2 = new Customer(usernameEnter.getText(), passwordEnter.getText());
            //NEW API: Need to test 
            if (owner.isCustomerMine(tc2)) {
                //int i = 0;
                for (int i = 0; i < owner.getCustomerList().size(); i++) {
                    Customer x = owner.getCustomerList().get(i);
                    if (x.getUsername().equals(tc2.getUsername()) && x.getPassword().equals(tc2.getPassword())) {
                        cCustomer = x;
                    }
                }
                customerStartScreen(primaryStage);

            }
            //
            usernameEnter.clear();
            passwordEnter.clear();
        });

        //VBox for arranging textfields and login button
        VBox loginScreenStuff = new VBox();
        loginScreenStuff.setPadding(new Insets(10));
        loginScreenStuff.setSpacing(10);
        loginScreenStuff.getChildren().addAll(welcomeMsg, usernameEnter, passwordEnter, loginButton);
        //Setting Scene
        Scene scene = new Scene(loginScreenStuff, 250, 250);

        //Setting Stage
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //OVERVIEW:
    /*Displays the owner-customers-screen, handles adding and deleting customers */
    public void ownerCustScreen(Stage primaryStage) {
        Owner owner = Owner.getInstance();
        //REQUIRES: Primary Stage
        //MODIFIES: 
        //EFFECTS: 

        //FileIO, based on Ethan's code, adjusted for customers file
        String readUsername, readPassword;
        int readPoints;
        owner.getCustomerList().clear();
        try {
            File userData = new File("customers.txt");
            if (userData.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File exists");
                Scanner in = new Scanner(userData);
                String ifContents = "";
                while (in.hasNext()) {
                    System.out.println("reading file");
                    ifContents += in.nextLine();
                    ifContents += "\n";
                }
                System.out.println("\n\n\n IFCONTENTS: \n" + ifContents + "\n\n\n");
                StringTokenizer tokenizer = new StringTokenizer(ifContents, "[\n");
                while (tokenizer.hasMoreTokens()) {

                    readUsername = tokenizer.nextToken();
                    System.out.println("UNTOKENS : " + readUsername);
                    readPassword = tokenizer.nextToken();
                    System.out.println("PWTOKENS : " + readPassword);
                    readPoints = Integer.parseInt(tokenizer.nextToken());
                    System.out.println("PTSTOKENS : " + readPoints);
                    //owner.addCustomer(readUsername, readPassword, readPoints);
                    Customer tempCust = new Customer(readUsername, readPassword);
                    tempCust.setPoints(readPoints);
                    tempCust.setStatus();
                    owner.addCustomer(tempCust);
                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }
        //Temporary Variables
        String tempUsername;
        String tempPassword;

        //Owner's customer list
//        ObservableList<Customer> ownerCustomerList = FXCollections.observableArrayList(owner.getCustomerList());
        // System.out.println("PTS: " + ownerCustomerList.get(0).getPoints());
        //Table
        TableView table = new TableView<Customer>();

        TableColumn<Customer, String> displayCustomerUsername = new TableColumn<>("Username");
        displayCustomerUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        displayCustomerUsername.setMinWidth(100);

        TableColumn<Customer, String> displayCustomerPassword = new TableColumn<Customer, String>("Password");
        displayCustomerPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        displayCustomerPassword.setMinWidth(100);

        /*
        TableColumn<Customer, Integer> displayCustomerPoints = new TableColumn<Customer, Integer>("Points");
        displayCustomerPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
        displayCustomerPoints.setMinWidth(70);
        */

        table.setItems(FXCollections.observableArrayList(Owner.getInstance().getCustomerList()));
        table.getColumns().addAll(displayCustomerUsername, displayCustomerPassword);

        //TextFields for input of username and password to add a customer
        TextField newUsernameInput = new TextField();
        newUsernameInput.setPromptText("username");
        TextField newPasswordInput = new TextField();
        newPasswordInput.setPromptText("password");

        //Buttons
        Button addButton = new Button();
        addButton.setText("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("ADDING!");
//                ObservableList<Customer> updatedUserData;
                boolean custEqual = false;
                Customer TC = new Customer(newUsernameInput.getText(), newPasswordInput.getText());
                for (Customer tc1 : Owner.getInstance().getCustomerList()) {
                    if (TC.getCustUsername().equals(tc1.getCustUsername()) && TC.getCustPassword().equals(tc1.getCustPassword())) {
                        System.out.println("NAMES ARE EQUAL");
                        custEqual = true;
                    }
                }
                if (custEqual) {
                    System.out.println("That username + password combo is already taken!");
//                    updatedUserData = FXCollections.observableArrayList(owner.getCustomerList());
//                    table.setItems(updatedUserData);
                } else {
                    owner.addCustomer(TC);
                    updateCustomerData();
                    System.out.println("ADDING CUSTOMER" + TC);
//                    updatedUserData = FXCollections.observableArrayList(owner.getCustomerList());
//                    table.setItems(updatedUserData);
//                    primaryStage.show();

                }
//                setTableItems(table, Owner.getInstance().getCustomerList());
                setTableItems(table, Owner.getInstance().getCustomerList());
                primaryStage.show();

                newUsernameInput.clear();
                newPasswordInput.clear();
            }
        });
//        ownerCustomerList = FXCollections.observableArrayList(Owner.getInstance().getCustomerList());

        //Delete Button and its event handler
        Button delButton = new Button();
        delButton.setText("Delete");
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("DELETING!");
//                ObservableList<Customer> updatedUserData = FXCollections.observableArrayList(owner.getCustomerList());
               
                 
                    Owner.getInstance().removeCustomer((Customer) table.getSelectionModel().getSelectedItem());
                       System.out.println("CUSTOMER DELETION INFO: "+(Customer) table.getSelectionModel().getSelectedItem());
//                System.out.println(table.getSelectionModel().getSelectedItem().getClass());
                    updateCustomerData();
                    setTableItems(table, Owner.getInstance().getCustomerList());
              
                    
                    if(table.getSelectionModel().getSelectedItem() == null){
System.out.println("NO Customer SELECTED" + e);
                    Customer c = new Customer(newUsernameInput.getText(), newPasswordInput.getText());
                    int x = -1;
                  for(int i = 0; i<owner.getCustomerList().size(); i++){
                      Customer g = owner.getCustomerList().get(i);
                      if(c.getUsername().equals(g.getUsername())&&c.getPassword().equals(g.getPassword())){
                          x = i;
                      }
                      
                  }
                  if(x>-1){
                      Owner.getInstance().removeCustomer(Owner.getInstance().getCustomerList().get(x));
                  }
                    System.out.println("book should be deleted");
                }
                    updateCustomerData();
                     setTableItems(table, Owner.getInstance().getCustomerList());
                primaryStage.show();
                    
//                updatedUserData = FXCollections.observableArrayList(owner.getCustomerList());
//                table.setItems(updatedUserData);
                primaryStage.show();
            }
        });

        //Back Button and event handler
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("GOING BACK!");
                ownerStartScreen(primaryStage);
            }
        });
//        ownerCustomerList = FXCollections.observableArrayList(Owner.getInstance().getCustomerList());

        //HBox that is going to contain back and delete button
        HBox bottomSection = new HBox();
        bottomSection.setPadding(new Insets(10));
        bottomSection.setSpacing(10);
        bottomSection.getChildren().addAll(backButton, delButton);
        bottomSection.setAlignment(Pos.BOTTOM_CENTER);

        //HBox for adding user
        HBox middleSection = new HBox();
        middleSection.setPadding(new Insets(10));
        middleSection.setSpacing(10);
        middleSection.getChildren().addAll(addButton, newUsernameInput, newPasswordInput);
        //Pane Stuff
        BorderPane root = new BorderPane();
        root.setTop(table);
        root.setCenter(middleSection);
        root.setBottom(bottomSection);
        //Scene stuff
        Scene scene = new Scene(root, 500, 500);
        //Stage setting
        primaryStage.setTitle("Owner Customer Screen");
        primaryStage.setScene(scene);
        primaryStage.show();

//        primaryStage.setScene(scene);
    }

    public void updateCustomerData() {
        Owner o = Owner.getInstance();
        ArrayList<Customer> customers = o.getCustomerList();
        try {
            File custData = new File("customers.txt");
            custData.delete();
            custData.createNewFile();
            FileWriter fw = new FileWriter(custData);
            for (Customer C : customers) {
                fw.write(C.getCustUsername() + "[" + C.getCustPassword() + "[" + C.getPoints() + "\n");
            }
            fw.close();
        } catch (IOException w) {
            System.out.println(w);
        }
    }

    public void setTableItems(TableView t, ArrayList<Customer> a) {
        ObservableList<Customer> ol = FXCollections.observableArrayList(Owner.getInstance().getCustomerList());
        t.setItems(ol);
    }

    public void Reciept_withCash(Stage primaryStage) {
        try{
        VBox pane = new VBox(10);
        //pane.setPadding(new Insets(10,10,10,10));
        pane.setAlignment(Pos.CENTER);
        
        cCustomer.setPoints(cCustomer.getPoints() + (int) Math.round(10 * purchasedBook.getPrice())); // Add 10x price amt of points to current customer point count
        //nodes within the pane!
        HBox node_1 = new HBox(10);
        Label lb_1 = new Label("Total Cost:");

        Label lb_2 = new Label("" + purchasedBook.getPrice());
        node_1.getChildren().addAll(lb_1, lb_2);
        node_1.setAlignment(Pos.CENTER);
        HBox node_2 = new HBox(10);
        Label lb_3 = new Label("Points");
        Label lb_4 = new Label("" + cCustomer.getPoints());
        node_2.getChildren().addAll(lb_3, lb_4);
        node_2.setAlignment(Pos.CENTER);
        HBox node_3 = new HBox(10);
        Label lb_5 = new Label("Status:");
        Label lb_6 = new Label("" + cCustomer.getStatus());
        node_3.getChildren().addAll(lb_5, lb_6);
        node_3.setAlignment(Pos.CENTER);
        try {
            FileWriter fw = new FileWriter("customers.txt");
            for (Customer c : Owner.getInstance().getCustomerList()) {
                fw.write(c.getCustUsername() + "[" + c.getCustPassword() + "[" + c.getPoints() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Button btLogout = new Button("Logout");
        btLogout.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cCustomer = null;
                loginScreen(primaryStage);
            }
        }
        );

        pane.getChildren().addAll(node_1, node_2, node_3, btLogout);
        Scene scene = new Scene(pane, 200, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Reciept");
        purchasedBook = null;
        primaryStage.show();
        primaryStage.setResizable(false);
        }catch(Exception ex){
            System.out.println("probably a nullpointer: " + ex);
        }
    }

    public void Reciept_withPoints(Stage primaryStage) {
        try{
        VBox pane = new VBox(10);
        //pane.setPadding(new Insets(10,10,10,10));

        pane.setAlignment(Pos.CENTER);
        //nodes within the pane!
        HBox node_1 = new HBox(10);
        Label lb_1 = new Label("Total Cost:");
        /*
        Total cost should display the price after points have been applied
        
        if points/100>bookPrice:  points = points -100*bookPrice, tempCost = 0
        if points/100<bookPrice: tempCost = bookPrice - points/100, points = 0;
        if points/100 == book price: tempCost = 0, bookPrice = 0
         */
        double tCost = purchasedBook.getPrice();
        if (cCustomer.getPoints() / 100 > purchasedBook.getPrice()) {
            cCustomer.setPoints(cCustomer.getPoints() - (int) Math.round(100 * purchasedBook.getPrice()));
            tCost = 0;
        } else {
            if (cCustomer.getPoints() / 100 < purchasedBook.getPrice()) {
                tCost = purchasedBook.getPrice() - cCustomer.getPoints() / 100.0;
                cCustomer.setPoints(0);

            } else {
                if (cCustomer.getPoints() / 100 == purchasedBook.getPrice()) {
                    tCost = 0;
                    cCustomer.setPoints(0);
                }
            }
        }
        try {
            FileWriter fw = new FileWriter("customers.txt");
            for (Customer c : Owner.getInstance().getCustomerList()) {
                fw.write(c.getCustUsername() + "[" + c.getCustPassword() + "[" + c.getPoints() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        Label lb_2 = new Label("" + tCost);
        node_1.getChildren().addAll(lb_1, lb_2);
        node_1.setAlignment(Pos.CENTER);
        HBox node_2 = new HBox(10);
        Label lb_3 = new Label("Points");
        Label lb_4 = new Label("" + cCustomer.getPoints());
        node_2.getChildren().addAll(lb_3, lb_4);
        node_2.setAlignment(Pos.CENTER);
        HBox node_3 = new HBox(10);
        Label lb_5 = new Label("Status:");
        Label lb_6 = new Label("" + cCustomer.getStatus());
        node_3.getChildren().addAll(lb_5, lb_6);
        node_3.setAlignment(Pos.CENTER);
        Button btLogout = new Button("Logout");
        btLogout.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cCustomer = null;
                loginScreen(primaryStage);
            }
        }
        );

        pane.getChildren().addAll(node_1, node_2, node_3, btLogout);
        Scene scene = new Scene(pane, 200, 200);
        purchasedBook = null;
        primaryStage.setScene(scene);
        primaryStage.setTitle("Reciept");
        primaryStage.show();
        primaryStage.setResizable(false);
         }catch(Exception ex){
            System.out.println("probably a nullpointer: " + ex);
        }
    }

    public void ownerStartScreen(Stage primaryStage) {
        VBox vb = new VBox(25);
        vb.setAlignment(Pos.CENTER);

        Button books = new Button("Books");
        Button customers = new Button("Customers");
        Button logout = new Button("Logout");

        books.setPrefSize(100, 30);
        customers.setPrefSize(100, 30);
        logout.setPrefSize(100, 30);

        books.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 12));
        customers.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 12));
        logout.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 12));

        vb.getChildren().addAll(books, customers, logout);
        primaryStage.setTitle("BookStore App");
        primaryStage.setScene(new Scene(vb, 350, 250));
        primaryStage.setResizable(false);
        primaryStage.show();

        books.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                createOwnerBookScreen(primaryStage);
            }
        }
        );
        customers.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ownerCustScreen(primaryStage);
            }
        }
        );
        logout.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cCustomer = null;
                loginScreen(primaryStage);
            }
        }
        );
    }

    public void createOwnerBookScreen(Stage primaryStage) {
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

                ownerStartScreen(primaryStage);
            }
        });

        HBox aAndb = new HBox();
        aAndb.getChildren().addAll(oLabel, backbtn);

        Button abtn = new Button("Add");                          //ADD BUTTON
        abtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(tfn.getText()); //returns the text entered in the textfield
                try {
                    Book c = new Book(tfn.getText(), Double.parseDouble(tfp.getText()));
                    inv.addBook(c);
                } catch (Exception e) {
                    System.out.println("AN ERROR HAS OCCURED: " + e);
                }
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
                try {
                    Book purchasedBook = table.getSelectionModel().getSelectedItem();
                    System.out.println("Removing Book " + purchasedBook.getName() + " That Costs " + purchasedBook.getPrice());
                    inv.deleteBook(purchasedBook);
                } catch (Exception e) {
                    System.out.println("NO BOOK SELECTED" + e);
                    Book c = new Book(tfn.getText(), Double.parseDouble(tfp.getText()));
                    inv.deleteBook(c);
                    System.out.println("book should be deleted");
                }
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

    public void customerStartScreen(Stage primaryStage) {
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


        Label wLabel = new Label("Welcome, You have " + cCustomer.getPoints() + " points");

        Button lbtn = new Button();
        lbtn.setText("Logout");
        lbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Logging Out");
                cCustomer = null;
                loginScreen(primaryStage);
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
                try{
                purchasedBook = table.getSelectionModel().getSelectedItem();
                System.out.println("Purchasing Book " + purchasedBook.getName() + " For " + purchasedBook.getPrice());
                inv.deleteBook(purchasedBook);
                } catch(Exception ex){
                    System.out.println("probably a null pointer lol: " + ex);
                }
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
                Reciept_withCash(primaryStage);
            }
        });
        Button rbtn = new Button();
        rbtn.setText("Redeem");
        rbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try{
                System.out.println("Redeeming");
                purchasedBook = table.getSelectionModel().getSelectedItem();
                System.out.println("Purchasing Book " + purchasedBook.getName() + " For " + purchasedBook.getPrice() + " Using Points");
                inv.deleteBook(purchasedBook);
                }catch(Exception ex){
                    System.out.println("PROBABLY A NULLPOINTER " + ex);
                }
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
                Reciept_withPoints(primaryStage);
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

}
