# Order MicroService
*Order MicroService for Grizzly Store* <br/>
:panda_face::panda_face::panda_face::panda_face::panda_face:
:panda_face::panda_face::panda_face::panda_face::panda_face:
:panda_face::panda_face::panda_face::panda_face::panda_face:

### Database Structure

* order_sum
  * order_id          [int]
  * status            [string]
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

- [ ] getOrderById (int OrderId);
- [ ] deleteOrderById (int OrderId);
- [ ] addOrder (Order order);
- [ ] addProductToOrder (OrderLine orderline);
- [ ] updateOrderStatus (String status,int orderId);
- [ ] addOrderLine (OrderLine orderline, int orderId);
- [ ] getAllOrder (int userId);
- [ ] getOrderByStatus (int userId, String status);

:octocat::octocat::octocat::octocat::octocat::octocat:
:octocat::octocat::octocat::octocat::octocat::octocat:
:octocat::octocat::octocat::octocat::octocat::octocat:
