# Order MicroService
*Order MicroService for Grizzly Store* <br/>
:panda_face::panda_face::panda_face::panda_face::panda_face:
:panda_face::panda_face::panda_face::panda_face::panda_face:
:panda_face::panda_face::panda_face::panda_face::panda_face:

### Database Structure

* order_sum
  * order_id          [int]
  * status            [string]
    * Pending   
    * Complete
    * Processing    *
    * Closed    *
    * Canceled  *
    * On Hold   *
  * total_price       [double]
  * user_id           [int][PK][FK]
  <br/>
* order_line
  * orderline_id       [int][PK]
  * product_id [int]
  * qty [int]
  * unit_price [double]
  * order_id [int][FK]
  
  
### API Functionality Requirement

- [x] getAllOrder (int userId);
- [x] getOrderByOrderId (int orderId);
- [x] getOrderByStatus (int userId, String status);
- [x] deleteOrderById (int orderId);
- [ ] addOrder (Order order);
- [ ] addProductToOrder (OrderLine orderline);
- [ ] addOrderLine (OrderLine orderline, int orderId);
- [ ] deleteOrderLine (OrderLine orderline, int orderId);
- [ ] updateOrderStatus (String status,int orderId);

:octocat::octocat::octocat::octocat::octocat::octocat:
:octocat::octocat::octocat::octocat::octocat::octocat:
:octocat::octocat::octocat::octocat::octocat::octocat:
