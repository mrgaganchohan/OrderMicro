package elixir.grizzly.orderMicro.Repositories;

import elixir.grizzly.orderMicro.Entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepo extends JpaRepository<OrderLine, Integer> {

    @Query("SELECT e FROM OrderLine e WHERE e.orderlineId=:id")
    OrderLine findByOrderlineId(int id);

}
