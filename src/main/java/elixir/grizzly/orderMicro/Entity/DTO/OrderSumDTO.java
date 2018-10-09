package elixir.grizzly.orderMicro.Entity.DTO;

import javax.validation.constraints.NotNull;

public class OrderSumDTO {
    private String status;

    @NotNull
    private int userId;


}
