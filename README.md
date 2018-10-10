# Order MicroService
*Order MicroService for Grizzly Store* <br/>
:panda_face::panda_face::panda_face::panda_face::panda_face:
:panda_face::panda_face::panda_face::panda_face::panda_face:
:panda_face::panda_face::panda_face::panda_face::panda_face:

### Database Structure

* order_sum
  * order_id          [int]
  * status            [string]
    * pending   
    * complete
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
  * sub_total [double]
  * order_id [int][FK]
  
  
### API Functionality Requirement

- [x] getAllOrder (int userId);
- [x] getOrderByOrderId (int orderId);
- [x] getOrderByStatus (int userId, String status);
- [x] getCartNum (int userId)
- [x] deleteOrderById (int orderId);
- [x] addProduct (int orderId, OrderLine orderline);
    - [x] Sub: addOrder (Order order);
- [x] delProduct (int orderId, OrderLine orderline);
    - [x] Sub: deleteOrder (Order order);
- [ ] updateOrderStatus (String status,int orderId);

:octocat::octocat::octocat::octocat::octocat::octocat:
:octocat::octocat::octocat::octocat::octocat::octocat:
:octocat::octocat::octocat::octocat::octocat::octocat:
