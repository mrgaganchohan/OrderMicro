package elixir.grizzly.orderMicro.Entity.DTO;

import javax.validation.constraints.NotNull;

public class OrderSumDTO {
    @NotNull
    private int userId;

    private double totalPrice;

    private String status;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
