package coe528groupproject;
import java.util.ArrayList;
/**
 *
 * @author Awais
 */
public class Inventory {
    private static Inventory invInstance;
    private static ArrayList<Book> bookList;

    private Inventory(){}
    
    //Getter method for singleton
    public static Inventory getInvInstance(){
        if (invInstance == null);
            invInstance = new Inventory();
            bookList = new ArrayList<Book>();
        return invInstance;
    }
    //isBookInInv method
    public boolean isBookInInv(Book b){
        for(Book c: bookList){
            if(b.equals(c)){
                return true;
            }
        }
        return false;
    }
    //addBook methdo
    public void addBook(Book b){
       try{
           if (b==null){
               throw new NullPointerException();
            
           }
           if((!(b.getName().equals("")))&& (b.getPrice()>=0)){
              bookList.add(b);
           }
       }
       catch(NullPointerException e){
           System.out.println("NullPointerException inside addBook method");
       }
    }
    //deleteBook method
    public void deleteBook(Book b){
        try{
            if(b==null){
                throw new NullPointerException();
            }
            if(!(b.getName().equals(""))&&(b.getPrice()>=0)){
                int i = 0;
                int index = -1;
              //  for(Book g: bookList){
                    for(int x= 0; x<bookList.size() ; x++){
                    System.out.println(i);
                    Book g = bookList.get(x);
                    if (g.getName().equals(b.getName()) && g.getPrice() == b.getPrice()){
                        i = -1; 
                        index = x;
                        
                    }
                }
                    if(i == -1){
                        System.out.println("BOOK IS EQUAL");
                        //bookList.remove(b);
                        bookList.remove(index);
                    }
                
            }
        }catch(NullPointerException e){
            
        }
    }
    //getBook method
    public Book getBook(String name){
        for (Book g: bookList){
            if(g.getName().equals(name)){
                return g;
            }
        }
        return null;
    }
    
    //getBookInvList method
    public static ArrayList<Book> getBookInvList(){
        return (ArrayList<Book>)bookList.clone();
    }
}