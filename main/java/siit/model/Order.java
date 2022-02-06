package siit.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private String number;
    private LocalDateTime placed;

    private int customerID;
    private Customer customer;

    private List<OrderProducts> orderProductsList = new ArrayList<>();

    public Order() {
    }

    public Order(int id, String number, LocalDateTime placed, int customerID) {
        this.id = id;
        this.number = number;
        this.placed = placed;
        this.customerID = customerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getPlaced() {
        return placed;
    }

    public void setPlaced(LocalDateTime placed) {
        this.placed = placed;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderProducts> getOrderProductsList() {
        return orderProductsList;
    }

    public void setOrderProductsList(List<OrderProducts> orderProductsList) {
        this.orderProductsList = orderProductsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId() == order.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + getNumber() + '\'' +
                ", placed=" + getPlaced() +
                ", customer=" + getCustomer().getName() +
                ", noOfItems=" + orderProductsList.size() +
                '}';
    }
}
