package elixir.grizzly.orderMicro.Repositories;

import elixir.grizzly.orderMicro.Entity.OrderLine;

import elixir.grizzly.orderMicro.Entity.OrderSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepo extends JpaRepository<OrderLine, Integer> {

    @Query("SELECT e FROM OrderLine e WHERE e.orderlineId=:id" )
    OrderLine findByProductId(int id);

    @Query("SELECT e FROM OrderLine e WHERE e.productId=:pid AND e.order=:ordersum")
    OrderLine findProductByPid(int pid, OrderSum ordersum);

}
