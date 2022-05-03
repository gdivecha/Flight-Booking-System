/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528groupproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;

/*
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.io.*;
import java.text.ParseException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Font;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.WindowEvent;
*/
/*
/**
 *
 * @author Student
 */
public class Receipt extends Application{
    
    @Override
    public void start(Stage primaryStage){
        Button btbuy = new Button();
        Button btredeempoints = new Button();
        Button btlogout = new Button();
        btbuy.addEventHandler(ActionEvent.ACTION, 
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e){
                            Reciept_withCash(primaryStage);
                        }
                    });
        btbuy.addEventHandler(ActionEvent.ACTION, 
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e){
                            Reciept_withPoints(primaryStage);
                        }
                    });
        btlogout.addEventHandler(ActionEvent.ACTION,
                    new EventHandler<ActionEvent>(){
                    @Override
                        public void handle(ActionEvent e){
                            //LoginScreen done by awais
                           // createLoginScreen(primaryStage);
                            System.out.println("Logout Button used!");
                        }
                    });
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                                            @Override
                                            public void handle (WindowEvent e){
                                                System.out.println("Window is closing!");
                                            }
        });
        Reciept_withCash(primaryStage);
    }
    
    public void Reciept_withCash(Stage primaryStage){
        VBox pane = new VBox(10);
        //pane.setPadding(new Insets(10,10,10,10));
        pane.setAlignment(Pos.CENTER);
        //nodes within the pane!
        HBox node_1 = new HBox(10);
        Label lb_1 = new Label("Total Cost:");
        Label lb_2 = new Label("");
        node_1.getChildren().addAll(lb_1, lb_2);
        node_1.setAlignment(Pos.CENTER);
        HBox node_2 = new HBox(10);
        Label lb_3 = new Label("Points");
        Label lb_4 = new Label("");
        node_2.getChildren().addAll(lb_3, lb_4);
        node_2.setAlignment(Pos.CENTER);
        HBox node_3 = new HBox(10);
        Label lb_5 = new Label("Status:");
        Label lb_6 = new Label("");
        node_3.getChildren().addAll(lb_5, lb_6);
        node_3.setAlignment(Pos.CENTER);
        Button btLogout = new Button("Logout");
        
        pane.getChildren().addAll(node_1, node_2, node_3, btLogout);
        Scene scene = new Scene(pane, 200, 200);
        
        primaryStage.setScene(scene); 
        primaryStage.setTitle("Reciept"); 
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    
    public void Reciept_withPoints(Stage primaryStage){
        VBox pane = new VBox(10);
        //pane.setPadding(new Insets(10,10,10,10));
        pane.setAlignment(Pos.CENTER);
        //nodes within the pane!
        HBox node_1 = new HBox(10);
        Label lb_1 = new Label("Total Cost:");
        Label lb_2 = new Label("");
        node_1.getChildren().addAll(lb_1, lb_2);
        node_1.setAlignment(Pos.CENTER);
        HBox node_2 = new HBox(10);
        Label lb_3 = new Label("Points");
        Label lb_4 = new Label("");
        node_2.getChildren().addAll(lb_3, lb_4);
        node_2.setAlignment(Pos.CENTER);
        HBox node_3 = new HBox(10);
        Label lb_5 = new Label("Status:");
        Label lb_6 = new Label("");
        node_3.getChildren().addAll(lb_5, lb_6);
        node_3.setAlignment(Pos.CENTER);
        Button btLogout = new Button("Logout");
        
        pane.getChildren().addAll(node_1, node_2, node_3, btLogout);
        Scene scene = new Scene(pane, 200, 200);
        
        primaryStage.setScene(scene); 
        primaryStage.setTitle("Reciept"); 
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    
    public static void main(String []args){
        launch(args);
    }
    
}
