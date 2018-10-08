package elixir.grizzly.orderMicro.Controller;


import elixir.grizzly.orderMicro.Entity.OrderLine;
import elixir.grizzly.orderMicro.Entity.OrderSum;
import elixir.grizzly.orderMicro.Repositories.OrderLineRepo;
import elixir.grizzly.orderMicro.Repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderLineRepo orderLineRepo;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<OrderSum> allOrder(){
        List<OrderSum> orderList = orderRepo.findAll();

        for(OrderSum ord : orderList)
        {
            List<OrderLine> ordLine = ord.getOrderline();
            ord.getOrderline().removeAll(ordLine);
        }
        return orderList;
    }

    @GetMapping(path = "/allOrder")
    public @ResponseBody Iterable<OrderSum> getAllOrder(){
        return orderRepo.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOrder(@PathVariable("id") final int id){
        OrderSum ord= orderRepo.findByOrderId(id);
        return new ResponseEntity<>(ord, HttpStatus.OK);
    }



}
