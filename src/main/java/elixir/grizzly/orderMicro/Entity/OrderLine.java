package elixir.grizzly.orderMicro.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Orderline_Id")
    private int orderlineId;

    @NotNull
    @Column(name = "Product_Id")
    private int productId;

    @NotNull
    @Column(name = "Unit_Price")
    private double unitPrice;

    @NotNull
    @Column(name = "Qty")
    private int qty;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "Order_Id", nullable = false)
    private OrderSum order;

    public int getOrderlineId() {
        return orderlineId;
    }

    public void setOrderlineId(int orderlineId) {
        this.orderlineId = orderlineId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    //Causing constant output for child array.
    @JsonIgnore
    public OrderSum getOrder() {
        return order;
    }

    public void setOrder(OrderSum order) {
        this.order = order;
    }
}
