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
public class Member extends Passenger{
    private int yearsOfMembership;
    
    public Member(String name, int age, int yearsOfMembership){
        super(name, age);
        this.yearsOfMembership = yearsOfMembership;
    }
    
    @Override
    public double applyDiscount(double p){
        if(this.yearsOfMembership>5){
            return 0.5*p;
        }
        else if(this.yearsOfMembership>1){
            return 0.9*p;
        }
        return 1.0*p;
    }
}
