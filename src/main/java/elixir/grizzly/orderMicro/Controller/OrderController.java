package elixir.grizzly.orderMicro.Controller;


import elixir.grizzly.orderMicro.Entity.DTO.OrderLineDTO;
import elixir.grizzly.orderMicro.Entity.OrderLine;
import elixir.grizzly.orderMicro.Entity.OrderSum;
import elixir.grizzly.orderMicro.Repositories.OrderLineRepo;
import elixir.grizzly.orderMicro.Repositories.OrderRepo;
import org.apache.commons.logging.LogFactory;
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
    private org.apache.commons.logging.Log Log = LogFactory.getLog(OrderController.class);


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
    //Need front end to test API callback.
    @GetMapping(path = "/user/{id}/cart")
    public ResponseEntity checkCart(@PathVariable("id") int id) {
        OrderSum count = orderRepo.findCartNum(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    //Wrapper try for two @RequestBody.
//    public class Wrapper{
//        private OrderSumDTO order;
//        private OrderLineDTO orderline;
//}

    //@AddProductToOrder
    @PostMapping(path = "/addOrder/{uid}", consumes = "application/json")
    public ResponseEntity addProduct(@RequestBody OrderLineDTO orderline, @PathVariable("uid") int uid) {
        OrderSum exist = orderRepo.findCartNum(uid);
        OrderLine m = new OrderLine();
        int productId = orderline.getProductId();
        int qty = orderline.getQty();
        double unitPrice = orderline.getUnitPrice();
        if (exist == null) {
            OrderSum n = new OrderSum();
            Log.info("Creating Initial Order!  ");
            n.setStatus("pending");
            n.setUserId(uid);
            orderRepo.save(n);
            //Initial OrderLine info
            m.setOrder(n);
            m.setQty(qty);
            m.setProductId(productId);
            m.setUnitPrice(unitPrice);
            m.setSubTotal(qty * unitPrice);
            orderLineRepo.save(m);
            n.setTotalPrice(m.getSubTotal());
            orderRepo.save(n);
            return new ResponseEntity<>("Thanks for starting your first order! ", HttpStatus.CREATED);
        } else {
            OrderLine product = orderLineRepo.findProductByPid(productId, exist);
            if (product != null) {
                product.setQty(product.getQty() + qty);
                product.setSubTotal(product.getSubTotal() + qty * unitPrice);
                orderLineRepo.save(product);
                exist.setTotalPrice(exist.getTotalPrice() + qty * unitPrice);
                orderRepo.save(exist);
                return new ResponseEntity<>("Gratz!! You have updated your cart!", HttpStatus.OK);
            } else {
                m.setOrder(exist);
                m.setQty(qty);
                m.setProductId(productId);
                m.setUnitPrice(unitPrice);
                m.setSubTotal(qty * unitPrice);
                orderLineRepo.save(m);
                exist.setTotalPrice(exist.getTotalPrice() + m.getSubTotal());
                orderRepo.save(exist);
                return new ResponseEntity<>("Gratz!! You have add it to your cart!", HttpStatus.CREATED);
            }
        }
    }

    //@DeleteProductFromOrder
    @DeleteMapping(path = "delOrder/{uid}")
    public ResponseEntity delProduct(@RequestBody OrderLineDTO orderline, @PathVariable("uid") int uid) {
        int productId = orderline.getProductId();
        OrderSum exist = orderRepo.findCartNum(uid);
        OrderLine existProduct = orderLineRepo.findProductByPid(productId, exist);
        if (exist == null) {
            return new ResponseEntity<>("Error ! You don't have unfinished order!", HttpStatus.NOT_FOUND);
        } else {
            if (existProduct == null) {
                return new ResponseEntity<>("Error !! You don't have this product in your cart!",
                        HttpStatus.NOT_FOUND);
            } else {
                exist.setTotalPrice(exist.getTotalPrice()-existProduct.getSubTotal());
                orderRepo.save(exist);
                orderLineRepo.deleteByOrderlineId(existProduct.getOrderlineId());
                return new ResponseEntity<>("Successfully deleted ", HttpStatus.OK);
            }
        }
    }

    //Sub@DeleteNewOrder
    @DeleteMapping(path = "/delete/user/{id}")
    public ResponseEntity delUndoneOrder(@PathVariable("id") int id) {
        OrderSum exist = orderRepo.findCartNum(id);
        if (exist == null) {
            return new ResponseEntity<>("You don't have unfinished order!", HttpStatus.NOT_FOUND);
        }
        orderRepo.deleteByOrderId(exist.getOrderId());
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

    }


//    //@UpdateOrderStatus
//    @PutMapping(path = "/status/{oid}")
//    public ResponseEntity updateStatus(@PathVariable("oid") int oid){
//
//    }


    //TESTing
    @GetMapping(path = "/test/{id}")
    public ResponseEntity testfun(@PathVariable("id") int id) {
        OrderSum exist = orderRepo.findCartNum(id);
        return new ResponseEntity<>(exist.getOrderline(), HttpStatus.OK);
    }
}
