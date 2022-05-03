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
public class ShoppingCart extends Subject{

    private ArrayList<Book> booksInCart;
    private int numOfBooksInCart;
    private double totalCost;
    private int pointsOfCust;
    
    public ShoppingCart(int pointsOfCust){
        this.numOfBooksInCart = 0;
        this.totalCost = 0.00;
        this.pointsOfCust = pointsOfCust;
        booksInCart = new ArrayList<Book>();
    }
    
    public void addBook(Book book){
        booksInCart.add(book);
        this.totalCost+=book.getPrice();
        this.numOfBooksInCart++;
    }
    
    public void deselectBook(Book book){
        if(booksInCart.contains(book)){
            booksInCart.remove(book);
            this.totalCost-=book.getPrice();
            this.numOfBooksInCart--;
        }
    }
    
    public double totalCost(){
        return this.totalCost;
    }
    
    public int getPoints(){
        return this.pointsOfCust;
    }
    
    public ArrayList<Book> getCartBooks(){
        return (ArrayList<Book>)booksInCart.clone();
    }
    
    public void buyBooks(){
//        if()
//          Display a message saying that the books chosen don't exist
//        }
        if(this.getCartBooks().size()<0){
//            no books chosen
        }
        else{
            int pointsToBeAdded = 10*(int)totalCost();
            this.pointsOfCust+=pointsToBeAdded;
            updateInv((ArrayList<Book>)this.booksInCart.clone());
        }
    }
    
    public void redeemAndBuyBooks(){
        int pointsToRedeem = this.pointsOfCust;
        int savings = pointsToRedeem/100;
        this.totalCost-=savings;;
        while(totalCost<0){
            totalCost++;
        }
        updateInv((ArrayList<Book>)this.booksInCart.clone());;
    }
}
