package coe528groupproject;

public class Customer extends User { //extends User

    private int points; // user Point Count
    private Status status; //user status (Silver/Gold) 
    private ShoppingCart shoppingCart;

    /**
     * Overview: Initializes Customer. By default, set Points to 0 and status to
     * Silver. Requires: Username and Password as string types, non null.
     * Modifies: None
     *
     * @param un The Username The client wishes to set to the Given Customer.
     * @param pwd The Password The client wishes to set to the Given Customer.
     */
    public Customer(String un, String pwd) {
        this.username = un;
        this.password = pwd;
        this.points = 0;
        this.status = new Silver();
        this.shoppingCart = new ShoppingCart(points);
    }

    /**
     * Overview: Getter method to retrieve Username of given customer c.
     * Requires: Customer c, c not null Modifies: None
     *
     * @param c the customer who's username is being requested.
     * @return the username of the given customer as specified by c.
     */
    public static String getCustUsername(Customer c) {
        return c.username;
    }

    /**
     * Overview: Getter method to retrieve Password of given customer c.
     * Requires: Customer c, c not null Modifies: None
     *
     * @param c the customer who's password is being requested.
     * @return the password of the given customer as specified by c.
     */
    public static String getCustPassword(Customer c) {
        return c.password;
    }

    /**
     * Overview: Getter method to return username of customer as specified by
     * INSERTCUSTOMERCLASSHERE.getCustUsername Requires: none Modifies: None
     *
     * @return Customer Username
     */
    public String getCustUsername() { //new API!
        return getCustUsername(this);
    }

    public String getUsername() {
        return getCustUsername(this);

    }

    /**
     * Overview Getter method to return password of customer as specified by
     * INSERTCUSTOMERCLASSHERE.getCustPassword Requires: none Modifies: None
     *
     * @return Customer password
     */
    public String getCustPassword() { //new API!
        return getCustPassword(this);
    }

    public String getPassword() {
        return getCustPassword(this);

    }
   
    /**
     * Overview: Get the quantity of points a user currently has. Requires: None
     * Modifies: This.points
     *
     * @return the quantity of points the user has accrued.
     */
    int getPoints() {
        return this.points;
    }

    /**
     * Overview Set user Point count to given value Requires: value of points to
     * be set Modifies: User point quantity.
     *
     * @param pts value with which users point count is being set to
     */
    void setPoints(int pts) {
        this.points = pts;
        this.setStatus();
    }

    /**
     * Overview set users Status variable to a given status Requires: Value of
     * status not equal to null. Modifies: User status
     *
     * @param status the status which the user's status variable is being set
     * to.
     */
    void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Overview: Automatically Sets users status correctly based on the quantity
     * of points they have. Requires: None Modifies: Status of user
     */
    void setStatus() {
        if (this.getPoints() < 1000) {
            this.setStatus(new Silver());
        } else {
            this.setStatus(new Gold());
        }

    }
    

    /**
     * Overview: Get the status that a user currently has. Requires: none
     * Modifies: none
     *
     * @return The Customers Current Status
     */
    Status getStatus() {
        return this.status;
    }

    /**
     * Overview: Shopping Cart Getter. Requires: None Modifies: None
     *
     * @return the shopping cart of the user
     */
    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public String toString() {
        return "\n ~~~~~USERINFO~~~~~ \nUser: " + this.username + "\n\nUSERNAME: " + this.username + "\nPASSWORD:" + this.password + "\nPOINTS:" + this.getPoints() + "\nSTATUS:" + this.getStatus() + "\n~~~~~ENDUSERINFO~~~~~";
    }
}
