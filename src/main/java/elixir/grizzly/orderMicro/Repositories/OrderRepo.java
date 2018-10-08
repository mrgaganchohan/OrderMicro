package elixir.grizzly.orderMicro.Repositories;

import elixir.grizzly.orderMicro.Entity.OrderSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderSum, Integer> {

    @Query("SELECT e FROM OrderSum e WHERE e.orderId=:id")
    OrderSum findByOrderId(int id);


}
