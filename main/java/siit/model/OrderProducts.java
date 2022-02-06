package siit.model;

import java.util.Objects;

public class OrderProducts {
    private int id;
    private int orderID;
    private int productID;
    private Number quantity;
    private Product product;
    private final String moneda = "RON";

    public OrderProducts() {
    }

    public OrderProducts(int id, int orderID, int productID, Number quantity) {
        this.id = id;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public OrderProducts(int id, int orderID, int productID, Number quantity, Product product) {
        this.id = id;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMoneda() {
        return moneda;
    }

    public double getValoare(){
        return calculateValue(getQuantity(), product.getPrice()).doubleValue();
    }

    private Number calculateValue(Number quantity, Number price){

        return quantity.doubleValue() * price.doubleValue();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProducts)) return false;
        OrderProducts that = (OrderProducts) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return product.getName() + ": " + getQuantity() + " " + product.getMu() + " x " + product.getPrice() + " = " + calculateValue(quantity, product.getPrice()) + " " + getMoneda();
    }
}
