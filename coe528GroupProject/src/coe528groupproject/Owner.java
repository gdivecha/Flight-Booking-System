/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528groupproject;

/**
 *
 * @author ahmedramadan
 */
import java.util.ArrayList;

public class Owner extends User {

    //Owner instance variables, inaddition to username&password from abstract user class.
    private static Owner ownerInstance = null;
    private ArrayList<Customer> cust;
    private Inventory inv;

    private Owner() {
        this.username = "admin";
        this.password = "admin";
        cust = new ArrayList<Customer>();
        inv = Inventory.getInvInstance();
    }

    public static Owner getInstance() {
        if (ownerInstance == null) {
            ownerInstance = new Owner();
            return ownerInstance;
        }
        return ownerInstance;
    }

    public ArrayList<Customer> getCustomerList() {
        return this.cust;
    }

    public boolean isCustomerMine(Customer c) {
        for (int i = 0; i < cust.size(); i++) {
            Customer temp_cust = cust.get(i);
            if ((temp_cust.getCustUsername().equals(c.getCustUsername()) && (temp_cust.getCustPassword().equals(c.getCustPassword())))) {
                return true;
            }
        }
        return false;
    }

    public void addCustomer(Customer c) {
        // if(!(isCustomerMine(c)==true)){
        c.setStatus();
        cust.add(c);
        // }
    }

    public void removeCustomer(Customer c) {
        cust.remove(c);
    }

}
