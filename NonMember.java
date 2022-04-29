/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author Gaurav Divecha
 */
public class NonMember extends Passenger{
    
    public NonMember(String name, int age){
        super(name, age);
    }
    
    @Override
    public double applyDiscount(double p){
        if(this.age>65){
            return 0.9*p;
        }
        return 1.0*p;
    }
}
