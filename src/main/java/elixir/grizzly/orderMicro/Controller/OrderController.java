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

    //GetAllOrderSum
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<OrderSum> allOrder() {
        List<OrderSum> orderList = orderRepo.findAll();
        for (OrderSum ord : orderList) {
            List<OrderLine> ordLine = ord.getOrderline();
            ord.getOrderline().removeAll(ordLine);
        }
        return orderList;
    }

    //GetAllOrder
    @GetMapping(path = "/allOrder")
    public @ResponseBody
    Iterable<OrderSum> getAllOrder() {
        return orderRepo.findAll();
    }

    //@GetOrderByOrderId
    @GetMapping(path = "/{id}")
    public ResponseEntity getOrder(@PathVariable("id") final int id) {
        OrderSum order = orderRepo.findByOrderId(id);
        if (order == null) {
            return new ResponseEntity<>("The order you were looking for doesn't exist." +
                    " You may have mistyped the address or the order may have been deleted.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //@GetOrderByUserId
    @GetMapping(path = "/user/{id}")
    public ResponseEntity getOrderByUser(@PathVariable("id") final int id) {
        List<OrderSum> order = orderRepo.findByUserId(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //@GetOrderByStatus
    @GetMapping(path = "/user/{id}/{stat}")
    public ResponseEntity getOrderByStatus(@PathVariable("id") int id, @PathVariable("stat") String stat) {
        List<OrderSum> order = orderRepo.findByStatus(id, stat);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //@DeleteOrderById
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity delOrder(@PathVariable("id") int id) {
        OrderSum order = orderRepo.findByOrderId(id);
        if (order == null) {
            return new ResponseEntity<>("The order you were looking for doesn't exist." +
                    " You may have mistyped the address or the order may have been deleted.", HttpStatus.NOT_FOUND);
        }
        orderRepo.deleteByOrderId(id);
        return new ResponseEntity<>("Delete Success!!!", HttpStatus.OK);
    }

    //@GetCartNum
    @GetMapping(path = "/user/{id}/cart")
    public ResponseEntity checkCart(@PathVariable("id") int id) {
        List<OrderSum> count = orderRepo.findCartNum(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


    //@AddNewOrder


//    public ResponseEntity getOrderByStatus(@PathVariable("stat") final String stat){
//        List<OrderSum> order = orderRepo.findByStatus(stat);
//        if (order == null) {
//            return new ResponseEntity<>("The order you were looking for doesn't exist." +
//                    " You may have mistyped the address or the order may have been deleted.", HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity<>(order, HttpStatus.OK);
//    }


}
