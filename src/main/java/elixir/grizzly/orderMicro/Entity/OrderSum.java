package elixir.grizzly.orderMicro.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
public class OrderSum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_Id")
    private int orderId;

    @NotNull
    @Column(name = "User_Id", unique = true)
    private int userId;

    @NotNull
    @Column(name = "Status")
    private String status;

    @Column(name = "Total_Price")
    private double totalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<OrderLine> orderline;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderLine> getOrderline() {
        return orderline;
    }

    public void setOrderline(List<OrderLine> orderline) {
        this.orderline = orderline;
    }
}
