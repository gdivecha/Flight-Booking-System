
package coe528groupproject;

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Awais
 */
public class Book {
    private StringProperty name;
    private DoubleProperty price;
    
    public Book(String n, double p){
        this.name = new SimpleStringProperty(n);
        this.price = new SimpleDoubleProperty(p);
    }
    //Getter Methods for Book
    public String getName(){
        return this.name.get();
    }
    public double getPrice(){
        return this.price.get();
    }
    
    @Override 
    public Book clone() throws CloneNotSupportedException{
        return (Book)super.clone();
    }
    
    @Override
    public int hashCode(){
        if(this==null){
            return -1;
        }
        final int primeNumber = 5;
        int hash = 1;
        hash = hash*primeNumber + (((this.name.get()==null)) ? 0:this.name.get().hashCode());
        hash = hash*primeNumber + (int) this.price.get();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (Double.doubleToLongBits(this.price.get()) != Double.doubleToLongBits(other.price.get())) {
            return false;
        }
        if (!Objects.equals(this.name.get(), other.name.get())) {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        Inventory inv = Inventory.getInvInstance();
        Book c = new Book("apple", 100);
        inv.addBook(new Book("apple", 100));
        
    }
}