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
public abstract class Observer {
    public abstract void updateInventory(final ArrayList<Book> books);
}
