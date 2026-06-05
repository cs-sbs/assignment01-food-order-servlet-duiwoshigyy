package cs.sbs.web.model;
public class Order {
    private static int nextId = 1001;
    private int orderId;
    private String customer;
    private String food;
    private int quantity;
    public Order(String customer, String food, int quantity) {
        this.orderId = nextId++;
        this.customer = customer;
        this.food = food;
        this.quantity = quantity;
    }
    public int getOrderId(){return orderId;}
    public String getCustomer(){return customer;}
    public String getFood(){return food;}
    public int getQuantity(){return quantity;}
}