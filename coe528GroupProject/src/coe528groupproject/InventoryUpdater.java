/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528groupproject;
import java.util.ArrayList;

/**
 *
 * @author Gaurav Divecha 
 */
public class InventoryUpdater extends Observer{
    public void updateInventory(final ArrayList<Book> books){
        Inventory i = Inventory.getInvInstance();
        if(Inventory.getBookInvList().size() > 0){
            for(Book b : books){
                boolean isBookInInventory = i.isBookInInv(b);
                if(isBookInInventory){
                    i.deleteBook(b);
                }
                else{
                    //Provide an error message saying these books were not available
                }
            }
        }
    }
}
